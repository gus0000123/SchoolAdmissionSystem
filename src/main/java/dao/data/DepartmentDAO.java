package dao.data;

import java.util.List;

import bean.data.Department;
import template.DAO;
import util.HibernateUtil;

public class DepartmentDAO implements DAO<Department>
{
	@Override
	public void insert(Department o) { HibernateUtil.insert(o); }

	@Override
	public void update(Department o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Department.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAll() { return (List<Department>) HibernateUtil.loadAll(Department.class); }

	@Override
	public Department getByPrimaryKey(int id) { return (Department) HibernateUtil.load(Department.class, id); }

	@Override
	public Department getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowByColumn(Department.class, "dept_id", 1, true);
		if (result != null && result.size() > 0)
			return (Department) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static DepartmentDAO instance;
	
	private DepartmentDAO() { }
	public static DepartmentDAO getInstance()
	{
		if (instance == null) instance = new DepartmentDAO();
		return instance;
	}
}
