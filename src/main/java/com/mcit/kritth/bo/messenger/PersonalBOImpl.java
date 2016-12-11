package com.mcit.kritth.bo.messenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.PersonalBO;
import com.mcit.kritth.dao.template.PersonalDAO;
import com.mcit.kritth.model.messenger.Personal;

@Service("personalService")
@Transactional
public class PersonalBOImpl implements PersonalBO
{
	@Autowired
	private PersonalDAO dao;
	
	@Override
	public void insert(Personal o) { dao.insertBean(o); }

	@Override
	public void update(Personal o) { dao.updateBean(o); }

	@Override
	public void delete(Personal o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Personal getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Personal> getAll() { return dao.getAllBeans(); }
	
	@Override
	public List<Personal> getAllFromReceiverId(int id)
	{	
		List<Personal> list = dao.getAllByReceiverId(id);
		List<Personal> result = new ArrayList<>();
		for (Personal pm : list)
		{
			if (!pm.isTrash())
				result.add(pm);
		}
		return result;
	}
	
	private final int SENT_LIMIT = 20;
	
	@Override
	public List<Personal> getAllFromSenderId(int id)
	{
		List<Personal> result = dao.getAllBySenderId(id);
		if (result.size() < SENT_LIMIT)
			return result;
		else
			return result.subList(0, SENT_LIMIT);
	}

	private final int TRASH_LIMIT = 20;
	
	@Override
	public List<Personal> getTrashFromReceiverId(int id)
	{
		List<Personal> list = dao.getAllByReceiverId(id);
		List<Personal> result = new ArrayList<>();
		for (Personal pm : list)
		{
			if (pm.isTrash())
				result.add(pm);
		}
		
		if (result.size() < TRASH_LIMIT)
			return result;
		else
			return result.subList(0, TRASH_LIMIT);
	}
}
