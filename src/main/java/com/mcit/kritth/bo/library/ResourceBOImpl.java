package com.mcit.kritth.bo.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.library.ResourceDAOImpl;
import com.mcit.kritth.model.library.Resource;

@Service("resourceService")
@Transactional
public class ResourceBOImpl implements BO<Resource>
{
	@Autowired
	private ResourceDAOImpl dao;
	
	@Override
	public void insert(Resource o) { dao.insertBean(o); }

	@Override
	public void update(Resource o) { dao.updateBean(o); }

	@Override
	public void delete(Resource o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Resource getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Resource> getAll() { return dao.getAllBeans(); }
}
