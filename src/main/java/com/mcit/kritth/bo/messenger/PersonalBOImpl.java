package com.mcit.kritth.bo.messenger;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.PersonalBO;
import com.mcit.kritth.dao.messenger.PersonalDAOImpl;
import com.mcit.kritth.model.messenger.Personal;

@Service("personalService")
@Transactional
public class PersonalBOImpl implements PersonalBO
{
	@Autowired
	private PersonalDAOImpl dao;
	
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
	public List<Personal> getAllFromReceiverId(int id) { return dao.getAllByReceiverId(id); }
	
	@Override
	public List<Personal> getAllFromSenderId(int id) { return dao.getAllBySenderId(id); }
}
