package com.mcit.kritth.dao.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class HibernateSupport
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() { return sessionFactory.getCurrentSession(); }
	
	public void setSessionFactory(SessionFactory session) { this.sessionFactory = session; }
	
	public void insert(Object o) { getSession().save(o); }
	
	public void update(Object o) { getSession().saveOrUpdate(o); }
	
	private void delete(Object o) { getSession().delete(o); }
	
	public void deleteById(Class c, Serializable id) { delete(load(c, id)); }
	
	public Object load(Class c, Serializable id)
	{
		Object o = getSession().load(c, id);
		Hibernate.initialize(o);
		return o;
	}
	
	public List loadAll(Class c)
	{
		String hql = "FROM " + c.getName();
		Query query = getSession().createQuery(hql);
		List results = query.getResultList();
		return results;
	}
	
	public List<Object> getNRowOrderByColumn(Class c, String columnName, int n, boolean fromLatest)
	{
		String hql = "FROM " + c.getName() + " ORDER BY " + columnName;
		if (fromLatest) hql += " DESC";
		else hql += " ASC";
		hql += " LIMIT " + n;
				
		List<Object> last = getSession().createQuery(hql).getResultList();
		
		if (last != null) return last;
		else return null;
	}
	
	// Mode value for getcondition
	public final int CONDITION_EQUAL = 0
			, CONDITION_GREATER = 1
			, CONDITION_LESSER = 2
			, CONDITION_LIKE = 3
			, CONDITION_NOT_EQUAL = 4
			, CONDITION_NOT_LIKE = 5
			, CONDITION_GREATER_EQUAL = 6
			, CONDITION_LESSER_EQUAL = 7;
	
	public <T, U> List<T> getCondition(int mode, int limit, Class c, String columnName, U value)
	{
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
		List<Object> obj = getSession().createQuery(hql).setParameter("value", value).getResultList();
		
		// Cast values
		List<T> list = new ArrayList<>();
		for (Object o : obj) { list.add((T) o); }
		
		return list;
	}
}
