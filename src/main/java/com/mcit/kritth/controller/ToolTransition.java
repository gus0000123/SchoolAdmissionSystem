package com.mcit.kritth.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.bo.template.UserBO;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.spring.ApplicationContextProvider;

/**
 * Servlet implementation class PageTransition
 */
@WebServlet("/toolTransition")
public class ToolTransition extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToolTransition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = HOME_URL;
		String action = request.getParameter("page");
		
		switch(action)
		{
			case "User":
				request.setAttribute("tab", "User");
				url = getUserContext(request, response);
				break;
			case "Person":
				request.setAttribute("tab", "Person");
				url = getPersonContext(request, response);
				break;
			case "Student":
				request.setAttribute("tab", "Student");
				url = getStudentContext(request, response);
				break;
		}
		
		getServletContext()
			.getRequestDispatcher(url)
			.forward(request, response);
	}
	
	private final String HOME_URL = "/home.jsp";
	private final String TOOL_URL = "/WEB-INF/jsp/admin/tool.jsp";
	
	private String getUserContext(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserBO service = ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class);
		
		ArrayList<String> tableColumn = new ArrayList<>();
		tableColumn.add("Username");
		tableColumn.add("Password");
		tableColumn.add("Person ID");
		tableColumn.add("Authority");
		
		request.setAttribute("tableHeader", tableColumn);
		
		List<User> list = service.getAll(); 
		if (list == null)
		{
			return HOME_URL;
		}
		else
		{
			request.setAttribute("list", list);
			return TOOL_URL;
		}
	}
	
	private String getPersonContext(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		
		ArrayList<String> tableColumn = new ArrayList<>();
		tableColumn.add("Person ID");
		tableColumn.add("First Name");
		tableColumn.add("Last Name");
		tableColumn.add("Address");
		tableColumn.add("Country");
		tableColumn.add("Telephone");
		tableColumn.add("Email");
		tableColumn.add("Sex");
		tableColumn.add("SIN");
		
		request.setAttribute("tableHeader", tableColumn);
		
		List<Person> list = service.getAll(); 
		if (list == null)
		{
			return HOME_URL;
		}
		else
		{
			request.setAttribute("list", list);
			return TOOL_URL;
		}
	}
	
	private String getStudentContext(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		StudentBO service = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		
		ArrayList<String> tableColumn = new ArrayList<>();
		tableColumn.add("Person ID");
		tableColumn.add("Admission Status");
		tableColumn.add("Major");
		tableColumn.add("Minor");
		tableColumn.add("Credit");
		tableColumn.add("Enrolment date");
		
		request.setAttribute("tableHeader", tableColumn);
		
		List<Student> list = service.getAll();
		if (list == null)
		{
			return HOME_URL;
		}
		else
		{
			request.setAttribute("list", list);
			return TOOL_URL;
		}
	}

}