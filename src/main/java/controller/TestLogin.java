package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.data.Person;
import bean.data.User;
import service.data.PersonService;
import service.data.UserService;
import util.BeanUtil;

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
		
		List<User> list = UserService.getInstance().getAll();
		boolean found_user = false;
		
		// Find user
		for (User u : list)
		{
			if (u.getUser().equals(user))
			{
				found_user = true;
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
				break;
			}
		}
		
		// Cannot find user
		if (!found_user)
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
			// Create person
			Person p = new Person();
			p.setFirstName(first_name);
			p.setMiddleName(middle_name);
			p.setLastName(last_name);
			p.setEmail(email);
			PersonService.getInstance().insert(p);
			
			// Create user
			User u = new User();
			u.setPerson(p);
			u.setPassword(password);
			u.setUser(BeanUtil.generateUserName(p));
			UserService.getInstance().insert(u);
			
			request.setAttribute("page", "confirmation");
		}
		else
		{
			request.setAttribute("error", "password");
		}
		
		return url;
	}
}