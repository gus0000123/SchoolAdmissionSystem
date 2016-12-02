package service.library;

import java.util.List;

import bean.library.ResourceType;
import dao.library.ResourceTypeDAO;
import template.Service;

public class ResourceTypeService implements Service<ResourceType>
{
	private ResourceTypeDAO dao;
	
	@Override
	public void insert(ResourceType o) { dao.insert(o); }

	@Override
	public void update(ResourceType o) { dao.update(o); }

	@Override
	public void delete(ResourceType o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public ResourceType getById(int id) { return dao.getByPrimaryKey(id); }
	
	@Override
	public List<ResourceType> getAll() { return dao.getAll(); }
	
	@Override
	public ResourceType getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static ResourceTypeService instance;
	public ResourceTypeService() { this.dao = ResourceTypeDAO.getInstance(); }
	
	public static ResourceTypeService getInstance()
	{
		if (instance == null) instance = new ResourceTypeService();
		return instance;
	}
}
