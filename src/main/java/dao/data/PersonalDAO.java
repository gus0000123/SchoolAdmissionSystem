package dao.data;

import java.util.List;

import bean.messenger.Personal;
import template.DAO;
import util.HibernateUtil;

public class PersonalDAO implements DAO<Personal>
{

	@Override
	public void insert(Personal o) { HibernateUtil.insert(o); }

	@Override
	public void update(Personal o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Personal.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Personal> getAll() { return (List<Personal>) HibernateUtil.loadAll(Personal.class); }

	@Override
	public Personal getByPrimaryKey(int id) { return (Personal) HibernateUtil.load(Personal.class, id);	}

	// Singleton
	private static PersonalDAO instance;
	
	private PersonalDAO() { }
	public static PersonalDAO getInstance()
	{
		if (instance == null) instance = new PersonalDAO();
		return instance;
	}
}
