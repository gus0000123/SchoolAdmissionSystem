package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserDAO implements DAO<User, String>
{

	public int insert(User o) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(User o) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int removeByPrimaryKey(String id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<User> getAll() throws SQLException, ClassNotFoundException
	{
		List<User> list = new ArrayList<>();
		
		ResultSet rs = con.executeQuery("SELECT * FROM USERS");
		while (rs.next())
		{
			User u = new User(rs.getString("username"), rs.getString("password"));
			u.setPersonID(rs.getInt("owner_id"));
			u.setAuthority(rs.getInt("authority"));
			list.add(u);
		}
		
		return list;
	}

	public User getByPrimaryKey(String id) throws SQLException, ClassNotFoundException
	{
		User u = null;
		ResultSet rs = con.executeQuery("SELECT * FROM USERS WHERE username = '" + id + "'");
		while (rs.next())
		{
			u = new User(rs.getString("username"), rs.getString("password"));
			u.setPersonID(rs.getInt("owner_id"));
			u.setAuthority(rs.getInt("authority"));
		}
		con.closeConnection(rs);
		return u;
	}

	/****************************SINGLETON**********************************/
	private static UserDAO instance = null;
	private MySQLConnection con = MySQLConnection.getInstance();
	private UserDAO() { }
	
	public static UserDAO getInstance()
	{
		if (instance == null)
			instance = new UserDAO();
		return instance;
	}
}
