package service.data;

import java.util.List;

import bean.data.Department;
import dao.data.DepartmentDAO;
import template.Service;

public class DepartmentService implements Service<Department>
{
	private DepartmentDAO dao;
	
	@Override
	public void insert(Department o) { dao.insert(o); }

	@Override
	public void update(Department o) { dao.update(o); }

	@Override
	public void delete(Department o) { dao.removeByPrimaryKey(o.getDeptId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Department getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Department> getAll() { return dao.getAll(); }
	
	@Override
	public Department getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static DepartmentService instance;
	public DepartmentService() { this.dao = DepartmentDAO.getInstance(); }
	
	public static DepartmentService getInstance()
	{
		if (instance == null) instance = new DepartmentService();
		return instance;
	}
}
