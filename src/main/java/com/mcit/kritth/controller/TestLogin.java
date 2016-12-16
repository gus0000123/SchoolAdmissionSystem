package com.mcit.kritth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.UserBO;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.spring.ApplicationContextProvider;
import com.mcit.kritth.util.BeanUtil;

/**
 * Servlet implementation class TestLogin
 */
@WebServlet("/TestLogin")
public class TestLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestLogin() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String url = "/";
		String action = request.getParameter("action");
		
		if (action != null)
		{
			switch(action)
			{
				case "login":
					request.setAttribute("page", "login");
					url = checkLogin(request);
					break;
				case "register":
					request.setAttribute("page", "register");
					url = checkRegister(request);
					break;
			}
		}
		else
		{
			request.setAttribute("error", "unknown");
		}
		
		// Forward
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	private String checkLogin(HttpServletRequest request)
	{
		String url = "/";
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		UserBO service = ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class);
		User u = service.getById(user);
		if (u != null)
		{
			if (u.getPassword().equals(password))
			{
				// Attach user to session
				request.getSession().setAttribute("user", u);
				url = "/TestTab?tab=overview";
			}
			else
			{
				request.setAttribute("error", "password");
			}
		}
		else
		{
			request.setAttribute("error", "user");
		}
		
		return url;
	}
	
	private String checkRegister(HttpServletRequest request)
	{
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		String url = "/";
		
		if (password.equals(cpassword))
		{
			PersonBO personService = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
			UserBO userService = ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class);
			
			// Create person
			Person p = new Person();
			p.setFirstName(first_name);
			p.setMiddleName(middle_name);
			p.setLastName(last_name);
			p.setEmail(email);
			personService.insert(p);
			
			// Create user
			User u = new User();
			u.setPerson(p);
			u.setPassword(password);
			u.setUser(BeanUtil.generateUserName(p));
			userService.insert(u);
			
			request.setAttribute("page", "confirmation");
			request.setAttribute("account_name", u.getUser());
		}
		else
		{
			request.setAttribute("error", "password");
		}
		
		return url;
	}
}