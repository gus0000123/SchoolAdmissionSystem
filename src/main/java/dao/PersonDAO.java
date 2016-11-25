package dao;

import java.util.List;

import beans.Person;
import hibernate.HibernateUtil;

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
	
	// Singleton
	private static PersonDAO instance = null;
	
	private PersonDAO() { }
	public static PersonDAO getInstance()
	{
		if (instance == null) instance = new PersonDAO();
		return instance;
	}
}
