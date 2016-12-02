package service.data;

import java.util.List;

import bean.data.Employee;
import dao.data.EmployeeDAO;
import template.Service;

public class EmployeeService implements Service<Employee>
{
	private EmployeeDAO dao;
	
	@Override
	public void insert(Employee o) { dao.insert(o); }

	@Override
	public void update(Employee o) { dao.update(o); }

	@Override
	public void delete(Employee o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Employee getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Employee> getAll() { return dao.getAll(); }
	
	@Override
	public Employee getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static EmployeeService instance;
	public EmployeeService() { this.dao = EmployeeDAO.getInstance(); }
	
	public static EmployeeService getInstance()
	{
		if (instance == null) instance = new EmployeeService();
		return instance;
	}
}
