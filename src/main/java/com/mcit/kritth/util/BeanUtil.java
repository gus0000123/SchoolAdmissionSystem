package com.mcit.kritth.util;

import java.util.List;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.spring.ApplicationContextProvider;

public class BeanUtil
{
	public static String generateUserName(Person p)
	{
		String user = p.getFirstName() + "." + p.getLastName();
		
		BO<User> service = ApplicationContextProvider.getService("userService");
		
		List<User> list = (List<User>) service.getAll();
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
