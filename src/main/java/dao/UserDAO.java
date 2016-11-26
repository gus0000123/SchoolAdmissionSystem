package dao;

import java.util.List;

import beans.User;
import hibernate.HibernateUtil;

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
		
	/****************************SINGLETON**********************************/
	private static UserDAO instance = null;
	private UserDAO() { }
	
	public static UserDAO getInstance()
	{
		if (instance == null) instance = new UserDAO();
		return instance;
	}
}
