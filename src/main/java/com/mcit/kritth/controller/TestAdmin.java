package com.mcit.kritth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.spring.ApplicationContextProvider;

/**
 * Servlet implementation class TestAdmin
 */
@WebServlet("/TestAdmin")
public class TestAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestAdmin() { super(); }

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
		String url;
		String tab = request.getParameter("tab");
		if (tab == null){ tab = ""; }
		
		switch(tab)
		{
			case "person":
				url = getPersonContext(request);
				break;
			default:
				url = "/TestTab?tab=overview";
				break;
		}
		
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	private String getPersonContext(HttpServletRequest request)
	{
		String url = "/WEB-INF/jsp/layout/adminApp.jsp";
		String mode = request.getParameter("mode");
		if (mode == null) { mode = "list"; }
		
		switch(mode)
		{
			case "list":
				PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
				List<Person> list = service.getAll();
				request.setAttribute("mode", "list");
				request.setAttribute("list", list);
				break;
			case "edit":
				request.setAttribute("mode", "edit");
				break;
			case "insert":
				request.setAttribute("mode", "insert");
				break;
		}
		
		request.setAttribute("tab", "person");
		
		return url;
	}
}
