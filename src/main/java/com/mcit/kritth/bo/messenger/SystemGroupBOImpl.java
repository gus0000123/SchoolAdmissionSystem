package com.mcit.kritth.bo.messenger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.messenger.SystemGroupDAOImpl;
import com.mcit.kritth.model.messenger.SystemGroup;

@Service("systemGroupService")
@Transactional
public class SystemGroupBOImpl implements BO<SystemGroup>
{
	@Autowired
	private SystemGroupDAOImpl dao;
	
	@Override
	public void insert(SystemGroup o) { dao.insertBean(o); }

	@Override
	public void update(SystemGroup o) { dao.updateBean(o); }

	@Override
	public void delete(SystemGroup o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public SystemGroup getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<SystemGroup> getAll() { return dao.getAllBeans(); }
}
