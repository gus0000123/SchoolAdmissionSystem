package service.data;

import java.util.List;

import bean.data.User;
import dao.data.UserDAO;
import template.Service;

public class UserService implements Service<User>
{
	private UserDAO dao;
	
	@Override
	public void insert(User o) { dao.insert(o); }

	@Override
	public void update(User o) { dao.update(o); }

	@Override
	public void delete(User o) { dao.removeByPrimaryKey(o.getUser_id()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public User getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<User> getAll() { return dao.getAll(); }
	
	// Singleton
	private static UserService instance;
	public UserService() { this.dao = UserDAO.getInstance(); }
	
	public static UserService getInstance()
	{
		if (instance == null) instance = new UserService();
		return instance;
	}
}
