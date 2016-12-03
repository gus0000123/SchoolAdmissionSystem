package dao.library;

import java.util.List;

import bean.library.Resource;
import template.DAO;
import util.HibernateUtil;

public class ResourceDAO implements DAO<Resource>
{
	@Override
	public void insert(Resource o) { HibernateUtil.insert(o); }

	@Override
	public void update(Resource o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Resource.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getAll() { return (List<Resource>) HibernateUtil.loadAll(Resource.class); }

	@Override
	public Resource getByPrimaryKey(int id) { return (Resource) HibernateUtil.load(Resource.class, id); }

	@Override
	public Resource getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(Resource.class, "id", 1, true);
		if (result != null && result.size() > 0)
			return (Resource) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static ResourceDAO instance;
	
	private ResourceDAO() { }
	public static ResourceDAO getInstance()
	{
		if (instance == null) instance = new ResourceDAO();
		return instance;
	}
}
