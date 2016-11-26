package dao;

import java.util.List;

import beans.DepartmentCode;
import hibernate.HibernateUtil;

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

	// Singleton
	private static DepartmentCodeDAO instance;
	
	private DepartmentCodeDAO() { }
	public static DepartmentCodeDAO getInstance()
	{
		if (instance == null) instance = new DepartmentCodeDAO();
		return instance;
	}
}
