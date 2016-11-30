package util;

import javax.servlet.http.HttpServletRequest;

import bean.data.Person;
import bean.data.User;
import dao.data.UserDAO;
import dao.messenger.PersonDAO;

public class FormUtil
{
	// TODO: Move this method to user service
	public static void insertUser(HttpServletRequest request)
	{
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		if (password.equals(cpassword))
		{
			Person p = new Person();
			p.setFirstName(first_name);
			if (middle_name != null) p.setMiddleName(middle_name);
			p.setLastName(last_name);
			p.setEmail(email);
			
			PersonDAO.getInstance().insert(p);
			
			// Get last person created
			p = (Person) HibernateUtil.getNRowByColumn(Person.class, "ID", 1, true);
			
			User user = new User();
			user.setUser(BeanUtil.generateUserName(p));
			user.setPassword(password);
			user.setPerson(p);
			
			UserDAO.getInstance().insert(user);
		}
		else
		{
			request.setAttribute("error", "password");
		}
	}
}
