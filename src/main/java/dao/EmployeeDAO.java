package dao;

import java.util.List;

import beans.Employee;
import hibernate.HibernateUtil;

public class EmployeeDAO implements DAO<Employee>
{

	@Override
	public void insert(Employee o) { HibernateUtil.insert(o); }

	@Override
	public void update(Employee o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.delete(id); }

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> getAll() { return (List<Employee>) HibernateUtil.loadAll(Employee.class); }

	@Override
	public Employee getByPrimaryKey(int id) { return (Employee) HibernateUtil.load(Employee.class, id); }

	// Singleton
	private static EmployeeDAO instance;
	
	private EmployeeDAO() { }
	public static EmployeeDAO getInstance()
	{
		if (instance == null) instance = new EmployeeDAO();
		return instance;
	}
}
