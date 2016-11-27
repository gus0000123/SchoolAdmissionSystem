package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.data.User;
import dao.data.*;

import java.util.List;

/**
 * Servlet implementation class PersonController
 */
@WebServlet("/login")
public class UserController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public UserController() { super(); }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
    {
    	HttpSession session = request.getSession();
    	
    	String url = "/index.jsp";
    	String user = request.getParameter("user");
    	String password = request.getParameter("password");
    	
    	UserDAO dao = UserDAO.getInstance();
    	List<User> list = dao.getAll();
    	
    	boolean userMatch = false;
    	boolean passwordMatch = false;
    	
    	// Check user and password
    	for (User u : list)
    	{
		    if (u.getUser().equals(user))
		    {
		    	userMatch = true;
		    	if (u.getPassword().equals(password))
		    	{
			    	url = "/pages/layout/homeApp.jsp";
			    	session.setAttribute("user", u);
			    	request.setAttribute("tab", "overview");
			    	passwordMatch = true;
		    	}
		    	break;
		    }
    	}
    	
    	// Sending error
    	if (!userMatch) request.setAttribute("error", "user");
    	else if (!passwordMatch) request.setAttribute("error", "password");
    	
    	// Forward
    	getServletContext()
    		.getRequestDispatcher(url)
    		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}