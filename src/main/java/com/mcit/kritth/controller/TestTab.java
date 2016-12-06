package com.mcit.kritth.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.ObjectNotFoundException;

import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.PersonalBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.model.messenger.Personal;
import com.mcit.kritth.spring.ApplicationContextProvider;

/**
 * Servlet implementation class TestParamServlet
 */
@WebServlet("/TestTab")
public class TestTab extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestTab() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String url = "/pages/layout/homeApp.jsp";
		String tab = request.getParameter("tab");
		
		User u = (User) request.getSession().getAttribute("user");
		
		// Session ended
		if (u == null)
		{
			url = "/";
			this.getServletContext().getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		// Session does not end and user is still around
		if (tab.equals("overview") && u.getPerson() != null)
		{
			getOverviewContext(request, u);
		}
		else if (tab.equals("messages") && u.getPerson() != null)
		{
			getMessageContext(request, u);
		}
		else if (tab.equals("application") && u.getPerson() != null)
		{
			getApplicationContext(request, u);
		}
		
		request.setAttribute("tab", request.getParameter("tab"));
		
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void getOverviewContext(HttpServletRequest request, User u)
	{
		Person p = u.getPerson();
		PersonalBO service = ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class);
		List<Personal> obj = service.getAllFromReceiverId(p.getID()); 
		List<Personal> nonImportant = new ArrayList<>();
		List<Personal> important = new ArrayList<>();
		
		// Sort
		Collections.sort(obj, new Comparator<Personal>()
		{
			@Override
			public int compare(Personal p1, Personal p2)
			{
				return p2.getCreation_time().compareTo(p1.getCreation_time());
			}
		});
		
		int limit = 3;			// Limit to show on each section, otherwise the page will be flooded
		boolean non_max = false;
		boolean imp_max = false;
		int non_counter = 0;
		int imp_counter = 0;
		
		for (Personal o : obj)
		{
			Personal pm = o;
			// Check which list to put into
			if (pm.isImportant())
			{
				if (imp_counter < limit) important.add(pm);
				else imp_max = true;
			}
			else
			{
				if (non_counter < limit) nonImportant.add(pm);
				else non_max = true;
			}
			
			if (imp_max && non_max) break;
		}
		
		request.setAttribute("important_messages", important);
		request.setAttribute("messages", nonImportant);
	}
	
	private void getMessageContext(HttpServletRequest request, User u)
	{
		// Initialize
		Person p = u.getPerson();
		List<Personal> list = null;
		Personal message = null;
		
		PersonalBO service = ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class);
		
		String action = request.getParameter("action");
		if (action == null || action.equals(""))
		{
			action = "other";
		}
		
		// Set sub tab
		if (action.equals("other"))
		{
			String sub = request.getParameter("sub_tab");
			if (sub == null) { sub = "inbox"; }
			switch(sub)
			{
				case "trash":
					list = service.getTrashFromReceiverId(p.getID());
					break;
				case "sent":
					list = service.getAllFromSenderId(p.getID());
					break;
				case "inbox":
				default:
					list = service.getAllFromReceiverId(p.getID());
					break;
			}		
			
			// Set messages
			if (list != null)
			{
				// Sort
				Collections.sort(list, new Comparator<Personal>()
				{
					@Override
					public int compare(Personal p1, Personal p2)
					{
						return p2.getCreation_time().compareTo(p1.getCreation_time());
					}
				});
			}
			else
			{
				list = new ArrayList<>();
			}
			request.setAttribute("all_messages", list);
			request.setAttribute("sub_tab", sub);
		}
		else if (action.equals("view"))
		{
			int message_id = Integer.parseInt(request.getParameter("select_message"));
			message = service.getById(message_id);
			
			request.setAttribute("mode", "view");
			request.setAttribute("message", message);
			request.setAttribute("action", "view");
		}
		else if (action.equals("compose"))
		{
			request.setAttribute("mode", "compose");
			request.setAttribute("action", "compose");
		}
	}
	
	private void getApplicationContext(HttpServletRequest request, User u)
	{
		// Initialize
		Person p = u.getPerson();
		StudentBO studentService = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		Student s = null;
		
		try { s = studentService.getById(p.getID()); }
		catch (ObjectNotFoundException ex) { }
		
		EmployeeBO employeeService = ApplicationContextProvider.getApplicationContext().getBean("employeeService", EmployeeBO.class);
		Employee e = null;
		
		try { e = employeeService.getById(p.getID()); }
		catch (ObjectNotFoundException ex) { }
		
		// attach
		request.setAttribute("student", s);
		request.setAttribute("employee", e);
	}
}
