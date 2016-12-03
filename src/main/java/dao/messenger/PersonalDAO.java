package dao.messenger;

import java.util.ArrayList;
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

	@Override
	public Personal getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(Personal.class, "message_id", 1, true);
		if (result != null && result.size() > 0)
			return (Personal) result.get(0);
		else
			return null;
	}
	
	public List<Personal> getAllByReceiverId(int id)
	{
		List<Object> list = HibernateUtil.getEqualIntCondition(Personal.class, "receiver_id", id);
		List<Personal> result = new ArrayList<>();
		if (list != null)
		{
			for (Object o : list)
			{
				result.add((Personal) o);
			}
		}
		return result;
	}
	
	// Singleton
	private static PersonalDAO instance;
	
	private PersonalDAO() { }
	public static PersonalDAO getInstance()
	{
		if (instance == null) instance = new PersonalDAO();
		return instance;
	}
}
