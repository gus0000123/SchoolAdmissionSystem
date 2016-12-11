package com.mcit.kritth.dao.messenger;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.AnnouncementDAO;
import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.model.messenger.Announcement;

@SuppressWarnings("unchecked")
@Repository("announcementDAO")
public class AnnouncementDAOImpl extends HibernateSupport implements AnnouncementDAO
{
	@Override
	public void insertBean(Announcement o) { insert(o); }

	@Override
	public void updateBean(Announcement o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Announcement.class, id); }

	@Override
	public List<Announcement> getAllBeans() { return (List<Announcement>) loadAll(Announcement.class); }

	@Override
	public Announcement getModelByPrimaryKey(Serializable id) { return (Announcement) load(Announcement.class, id); }
}
