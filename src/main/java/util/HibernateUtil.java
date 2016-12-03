package util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
	/**
	 * Get session for hibernate. Make sure to manually close section when using this method
	 * @return session created by hibernate
	 */
	public static Session getSession()
	{
		if (sessionFactory == null)
			sessionFactory = getSessionFactory();
		return sessionFactory.openSession();
	}
	
	public static void insert(Object o)
	{
		Session session = getSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void update(Object o)
	{
		Session session = getSession();
		session.beginTransaction();
		session.update(o);
		session.getTransaction().commit();
		session.close();
	}
	
	private static void delete(Object o)
	{
		Session session = getSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("rawtypes")
	public static void deleteById(Class c, int id)
	{
		delete(load(c, id));
	}
	
	@SuppressWarnings("rawtypes")
	public static Object load(Class c, int id)
	{
		Session session = getSession();
		session.beginTransaction();
		
		Object o = session.load(c, id);
		Hibernate.initialize(o);
		
		session.close();
		
		return o;
	}
	
	@SuppressWarnings("rawtypes")
	public static List loadAll(Class c)
	{
		Session session = getSession();
		session.beginTransaction();
		
		String hql = "FROM " + c.getName();
		Query query = session.createQuery(hql);
		List results = query.list();
		
		session.close();
		return results;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Object> getNRowOrderByColumn(Class c, String columnName, int n, boolean fromLatest)
	{
		Session session = getSession();
		session.beginTransaction();
		
		String hql = "FROM " + c.getName() + " ORDER BY " + columnName;
		if (fromLatest) hql += " DESC";
		else hql += " ASC";
		hql += " LIMIT " + n;
				
		List<Object> last = session.createQuery(hql).list();
		
		session.close();
		
		if (last != null) return last;
		else return null;
	}
	
	// Mode value for getcondition
	public static final int CONDITION_EQUAL = 0
			, CONDITION_GREATER = 1
			, CONDITION_LESSER = 2
			, CONDITION_LIKE = 3
			, CONDITION_NOT_EQUAL = 4
			, CONDITION_NOT_LIKE = 5
			, CONDITION_GREATER_EQUAL = 6
			, CONDITION_LESSER_EQUAL = 7;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T, U> List<T> getCondition(int mode, int limit, Class c, String columnName, U value)
	{
		// Get session
		Session session = getSession();
		session.beginTransaction();
		
		// Check if supported type and translated to string
		if (value == null) { return new ArrayList<T>(); }
		
		// Get correct sign
		String sign = "";
		switch(mode)
		{
			case CONDITION_EQUAL: sign = " = "; break;
			case CONDITION_GREATER: sign = " > "; break;
			case CONDITION_LESSER: sign = " < "; break;
			case CONDITION_LIKE: sign = " like "; break;
			case CONDITION_NOT_EQUAL: sign = " <> "; break;
			case CONDITION_NOT_LIKE: sign = " not like "; break;
			case CONDITION_GREATER_EQUAL: sign = " >= "; break;
			case CONDITION_LESSER_EQUAL: sign = " <= "; break;
			default:
				return new ArrayList<T>();
		}
		
		// Create query
		String hql = "FROM " + c.getName() + " WHERE " + columnName + sign + ":value";
		Query query = session.createQuery(hql);
		query.setParameter("value", value);
		List<Object> obj = query.list();
		
		// Cast values
		List<T> list = new ArrayList<>();
		for (Object o : obj) { list.add((T) o); }
		
		session.close();
		return list;
	}
	
	private HibernateUtil() { }
}
