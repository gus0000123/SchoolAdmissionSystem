package dao.messenger;

import java.util.List;

import bean.messenger.SystemGroup;
import template.DAO;
import util.HibernateUtil;

public class SystemGroupDAO implements DAO<SystemGroup>
{

	@Override
	public void insert(SystemGroup o) { HibernateUtil.insert(o); }

	@Override
	public void update(SystemGroup o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(SystemGroup.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemGroup> getAll() { return (List<SystemGroup>) HibernateUtil.loadAll(SystemGroup.class); }

	@Override
	public SystemGroup getByPrimaryKey(int id) { return (SystemGroup) HibernateUtil.load(SystemGroup.class, id); }

	@Override
	public SystemGroup getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowByColumn(SystemGroup.class, "sys_group_id", 1, true);
		if (result != null && result.size() > 0)
			return (SystemGroup) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static SystemGroupDAO instance;
	
	private SystemGroupDAO() { }
	public static SystemGroupDAO getInstance()
	{
		if (instance == null) instance = new SystemGroupDAO();
		return instance;
	}
}
