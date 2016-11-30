package util;

import java.util.List;
import java.util.ArrayList;

import bean.data.Person;
import bean.messenger.Personal;

public class FormUtil
{
	public static List<Personal> getMessageFor(Person p)
	{
		List<Object> obj = HibernateUtil.getEqualIntCondition(Personal.class, "receiver_id", p.getID());
		List<Personal> list = new ArrayList<>();
		for (Object o : obj)
		{ 
			list.add((Personal) o);
		}
			
		return list;
	}
}
