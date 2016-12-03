package dao.data;

import java.util.List;

import bean.data.Person;
import template.DAO;
import util.HibernateUtil;

public class PersonDAO implements DAO<Person>
{
	@Override
	public void insert(Person o) { HibernateUtil.insert(o); }

	@Override
	public void update(Person o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Person.class, id); }

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getAll() { return HibernateUtil.loadAll(Person.class); }

	@Override
	public Person getByPrimaryKey(int id) { return (Person) HibernateUtil.load(Person.class, id); }
	
	@Override
	public Person getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(Person.class, "ID", 1, true);
		if (result != null && result.size() > 0)
			return (Person) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static PersonDAO instance = null;
	
	private PersonDAO() { }
	public static PersonDAO getInstance()
	{
		if (instance == null) instance = new PersonDAO();
		return instance;
	}
}