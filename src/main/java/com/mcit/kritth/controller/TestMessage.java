package com.mcit.kritth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.kritth.bo.template.PersonalBO;
import com.mcit.kritth.model.messenger.Personal;
import com.mcit.kritth.spring.ApplicationContextProvider;

/**
 * Servlet implementation class Message
 */
@WebServlet("/TestMessage")
public class TestMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String sub_tab = request.getParameter("sub_tab");
		String url = "/TestTab?tab=messages&sub_tab=" + sub_tab;
		
		switch(sub_tab)
		{
			case "trash":
				trashAction(request);
				break;
			case "inbox":
				inboxAction(request);
			default:
				break;
		}
		
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	private void inboxAction(HttpServletRequest request)
	{
		PersonalBO service = ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class);
		
		String[] selection = request.getParameterValues("selection");
		
		for (int i = 0; i < selection.length; i++)
		{
			int id = Integer.parseInt(selection[i]);
			Personal pm = service.getById(id);
			pm.setTrash(true);
			service.update(pm);
		}
	}

	private void trashAction(HttpServletRequest request)
	{
		PersonalBO service = ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class);
		
		String[] selection = request.getParameterValues("selection");
		
		for (int i = 0; i < selection.length; i++)
		{
			int id = Integer.parseInt(selection[i]);
			Personal pm = service.getById(id);
			pm.setTrash(false);
			service.update(pm);
		}
	}
}
