package dao.messenger;

import java.util.List;

import bean.messenger.Announcement;
import template.DAO;
import util.HibernateUtil;

public class AnnouncementDAO implements DAO<Announcement>
{

	@Override
	public void insert(Announcement o) { HibernateUtil.insert(o); }

	@Override
	public void update(Announcement o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Announcement.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Announcement> getAll() { return (List<Announcement>) HibernateUtil.loadAll(Announcement.class); }

	@Override
	public Announcement getByPrimaryKey(int id) { return (Announcement) HibernateUtil.load(Announcement.class, id); }
	
	// Singleton
	private static AnnouncementDAO instance;
	
	private AnnouncementDAO() { }
	public static AnnouncementDAO getInstance()
	{
		if (instance == null) instance = new AnnouncementDAO();
		return instance;
	}
}
