package dao.data;

import java.util.List;

import bean.data.DepartmentCode;
import template.DAO;
import util.HibernateUtil;

public class DepartmentCodeDAO implements DAO<DepartmentCode>
{
	@Override
	public void insert(DepartmentCode o) { HibernateUtil.insert(o); }

	@Override
	public void update(DepartmentCode o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(DepartmentCode.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartmentCode> getAll() { return (List<DepartmentCode>) HibernateUtil.loadAll(DepartmentCode.class); }

	@Override
	public DepartmentCode getByPrimaryKey(int id) { return (DepartmentCode) HibernateUtil.load(DepartmentCode.class, id); }

	@Override
	public DepartmentCode getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowByColumn(DepartmentCode.class, "id", 1, true);
		if (result != null && result.size() > 0)
			return (DepartmentCode) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static DepartmentCodeDAO instance;
	
	private DepartmentCodeDAO() { }
	public static DepartmentCodeDAO getInstance()
	{
		if (instance == null) instance = new DepartmentCodeDAO();
		return instance;
	}
}
