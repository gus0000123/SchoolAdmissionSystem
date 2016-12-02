package dao.data;

import java.util.List;

import bean.data.User;
import template.DAO;
import util.HibernateUtil;

public class UserDAO implements DAO<User>
{
	@Override
	public void insert(User o) { HibernateUtil.insert(o); }

	@Override
	public void update(User o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(User.class, id); }
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAll() { return (List<User>) HibernateUtil.loadAll(User.class); }

	@Override
	public User getByPrimaryKey(int id) { return (User) HibernateUtil.load(User.class, id); }
	
	@Override
	public User getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowByColumn(User.class, "user_id", 1, true);
		if (result != null && result.size() > 0)
			return (User) result.get(0);
		else
			return null;
	}
		
	/****************************SINGLETON**********************************/
	private static UserDAO instance = null;
	private UserDAO() { }
	
	public static UserDAO getInstance()
	{
		if (instance == null) instance = new UserDAO();
		return instance;
	}
}
