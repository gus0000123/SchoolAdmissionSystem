package service.messenger;

import java.util.List;

import bean.messenger.Announcement;
import dao.messenger.AnnouncementDAO;
import template.Service;

public class AnnouncementService implements Service<Announcement>
{
	private AnnouncementDAO dao;
	
	@Override
	public void insert(Announcement o) { dao.insert(o); }

	@Override
	public void update(Announcement o) { dao.update(o); }

	@Override
	public void delete(Announcement o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Announcement getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Announcement> getAll() { return dao.getAll(); }
	
	@Override
	public Announcement getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static AnnouncementService instance;
	public AnnouncementService() { this.dao = AnnouncementDAO.getInstance(); }
	
	public static AnnouncementService getInstance()
	{
		if (instance == null) instance = new AnnouncementService();
		return instance;
	}
}
