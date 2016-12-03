package service.data;

import java.util.List;

import bean.data.Person;
import dao.data.PersonDAO;
import template.Service;

public class PersonService implements Service<Person>
{
	private PersonDAO dao;
	
	@Override
	public void insert(Person o) { dao.insert(o); }

	@Override
	public void update(Person o) { dao.update(o); }

	@Override
	public void delete(Person o) { dao.removeByPrimaryKey(o.getID()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Person getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Person> getAll() { return dao.getAll(); }
	
	// Singleton
	private static PersonService instance;
	public PersonService() { this.dao = PersonDAO.getInstance(); }
	
	public static PersonService getInstance()
	{
		if (instance == null) instance = new PersonService();
		return instance;
	}
}
