package com.mcit.kritth.bo.library;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.ResourceBO;
import com.mcit.kritth.dao.template.ResourceDAO;
import com.mcit.kritth.model.library.Resource;

@Service("resourceService")
@Transactional
public class ResourceBOImpl implements ResourceBO
{
	@Autowired
	private ResourceDAO dao;
	
	@Override
	public void insert(Resource o) { dao.insertBean(o); }

	@Override
	public void update(Resource o) { dao.updateBean(o); }

	@Override
	public void delete(Resource o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Resource getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Resource> getAll() { return dao.getAllBeans(); }
}
