package util;

import java.util.List;

import bean.data.Course;
import bean.data.Person;
import bean.data.User;
import dao.data.UserDAO;

public class BeanUtil
{
	public static String generateUserName(Person p)
	{
		String user = p.getFirstName() + "." + p.getLastName();
		List<User> list = UserDAO.getInstance().getAll();
		int counter = 1;
		String postFix = "";
		// Fetching legit username
		for (User u : list)
		{
			if (u.getUser().equals(user + postFix))
			{
				postFix = "" + counter;
				counter++;
			}
		}
		
		return user + postFix;
	}
	

	public static String getCourseCode(Course c)
	{
		return c.getClass_level() + "-"
				+ ConverterUtil.fillLeft("" + c.getCourse_id(), '0', 2) + "-"
				+ ConverterUtil.fillLeft("" + c.getSection(), '0', 2); 
	}
}
