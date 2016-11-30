package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.data.Person;
import bean.data.User;
import bean.messenger.Personal;
import util.HibernateUtil;

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
		
		User u = (User) request.getSession().getAttribute("user");
		if (u.getPerson() != null)
		{
			Person p = u.getPerson();
			List<Object> obj = HibernateUtil.getEqualIntCondition(Personal.class, "receiver_id", p.getID());
			List<Personal> nonImportant = new ArrayList<>();
			List<Personal> important = new ArrayList<>();
			
			int limit = 3;
			boolean non_max = false;
			boolean imp_max = false;
			int non_counter = 0;
			int imp_counter = 0;
			
			for (Object o : obj)
			{
				Personal pm = (Personal) o;
				if (pm.isImportant())
				{
					if (imp_counter < limit)
					{
						important.add(pm);
					}
					else
					{
						imp_max = true;
					}
				}
				else
				{
					if (non_counter < limit)
					{
						nonImportant.add(pm);
					}
					else
					{
						non_max = true;
					}
				}
				
				if (imp_max && non_max) break;
			}
			
			request.setAttribute("important_messages", important);
			request.setAttribute("messages", nonImportant);
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

}
