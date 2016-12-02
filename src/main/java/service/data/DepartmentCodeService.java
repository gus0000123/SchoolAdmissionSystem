package service.data;

import java.util.List;

import bean.data.DepartmentCode;
import dao.data.DepartmentCodeDAO;
import template.Service;

public class DepartmentCodeService implements Service<DepartmentCode>
{
	private DepartmentCodeDAO dao;
	
	@Override
	public void insert(DepartmentCode o) { dao.insert(o); }

	@Override
	public void update(DepartmentCode o) { dao.update(o); }

	@Override
	public void delete(DepartmentCode o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public DepartmentCode getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<DepartmentCode> getAll() { return dao.getAll(); }
	
	@Override
	public DepartmentCode getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static DepartmentCodeService instance;
	public DepartmentCodeService() { this.dao = DepartmentCodeDAO.getInstance(); }
	
	public static DepartmentCodeService getInstance()
	{
		if (instance == null) instance = new DepartmentCodeService();
		return instance;
	}
}
