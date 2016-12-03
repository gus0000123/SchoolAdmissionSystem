package service.library;

import java.util.List;

import bean.library.Resource;
import dao.library.ResourceDAO;
import template.Service;

public class ResourceService implements Service<Resource>
{
	private ResourceDAO dao;
	
	@Override
	public void insert(Resource o) { dao.insert(o); }

	@Override
	public void update(Resource o) { dao.update(o); }

	@Override
	public void delete(Resource o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Resource getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Resource> getAll() { return dao.getAll(); }
	
	// Singleton
	private static ResourceService instance;
	public ResourceService() { this.dao = ResourceDAO.getInstance(); }
	
	public static ResourceService getInstance()
	{
		if (instance == null) instance = new ResourceService();
		return instance;
	}
}
