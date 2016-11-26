package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Person;
import beans.Student;
import beans.User;
import dao.PersonDAO;
import dao.StudentDAO;
import dao.UserDAO;

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
	private final String TOOL_URL = "/pages/admin/tool.jsp";
	
	private String getUserContext(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserDAO dao = UserDAO.getInstance();
		
		ArrayList<String> tableColumn = new ArrayList<>();
		tableColumn.add("Username");
		tableColumn.add("Password");
		tableColumn.add("Person ID");
		tableColumn.add("Authority");
		
		request.setAttribute("tableHeader", tableColumn);
		
		List<User> list = dao.getAll(); 
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
		PersonDAO dao = PersonDAO.getInstance();
		
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
		
		List<Person> list = dao.getAll(); 
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
		StudentDAO dao = StudentDAO.getInstance();
		
		ArrayList<String> tableColumn = new ArrayList<>();
		tableColumn.add("Person ID");
		tableColumn.add("Admission Status");
		tableColumn.add("Major");
		tableColumn.add("Minor");
		tableColumn.add("Credit");
		tableColumn.add("Enrolment date");
		
		request.setAttribute("tableHeader", tableColumn);
		
		List<Student> list = dao.getAll();
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
