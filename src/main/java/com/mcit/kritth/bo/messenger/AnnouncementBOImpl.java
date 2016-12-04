package com.mcit.kritth.bo.messenger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.messenger.AnnouncementDAOImpl;
import com.mcit.kritth.model.messenger.Announcement;

@Service("announcementService")
@Transactional
public class AnnouncementBOImpl implements BO<Announcement>
{
	@Autowired
	private AnnouncementDAOImpl dao;
	
	@Override
	public void insert(Announcement o) { dao.insertBean(o); }

	@Override
	public void update(Announcement o) { dao.updateBean(o); }

	@Override
	public void delete(Announcement o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Announcement getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Announcement> getAll() { return dao.getAllBeans(); }
}
