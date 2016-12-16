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
		
		String url = "/WEB-INF/jsp/layout/adminApp.jsp";
		
		PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		List<Person> list = service.getAll();
		
		request.setAttribute("list", list);
		
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
