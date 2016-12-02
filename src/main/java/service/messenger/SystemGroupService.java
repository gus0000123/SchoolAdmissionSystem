package service.messenger;

import java.util.List;

import bean.messenger.SystemGroup;
import dao.messenger.SystemGroupDAO;
import template.Service;

public class SystemGroupService implements Service<SystemGroup>
{
	private SystemGroupDAO dao;
	
	@Override
	public void insert(SystemGroup o) { dao.insert(o); }

	@Override
	public void update(SystemGroup o) { dao.update(o); }

	@Override
	public void delete(SystemGroup o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public SystemGroup getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<SystemGroup> getAll() { return dao.getAll(); }
	
	@Override
	public SystemGroup getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static SystemGroupService instance;
	public SystemGroupService() { this.dao = SystemGroupDAO.getInstance(); }
	
	public static SystemGroupService getInstance()
	{
		if (instance == null) instance = new SystemGroupService();
		return instance;
	}
}
