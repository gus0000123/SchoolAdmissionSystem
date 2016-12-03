package dao.data;

import java.util.List;

import bean.data.Employee;
import template.DAO;
import util.HibernateUtil;

public class EmployeeDAO implements DAO<Employee>
{

	@Override
	public void insert(Employee o) { HibernateUtil.insert(o); }

	@Override
	public void update(Employee o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Employee.class, id); }

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> getAll() { return (List<Employee>) HibernateUtil.loadAll(Employee.class); }

	@Override
	public Employee getByPrimaryKey(int id) { return (Employee) HibernateUtil.load(Employee.class, id); }

	@Override
	public Employee getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(Employee.class, "id", 1, true);
		if (result != null && result.size() > 0)
			return (Employee) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static EmployeeDAO instance;
	
	private EmployeeDAO() { }
	public static EmployeeDAO getInstance()
	{
		if (instance == null) instance = new EmployeeDAO();
		return instance;
	}
}
