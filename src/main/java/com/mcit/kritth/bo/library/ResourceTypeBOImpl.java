package com.mcit.kritth.bo.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.library.ResourceTypeDAOImpl;
import com.mcit.kritth.model.library.ResourceType;

@Service("resourceTypeService")
@Transactional
public class ResourceTypeBOImpl implements BO<ResourceType>
{
	@Autowired
	private ResourceTypeDAOImpl dao;
	
	@Override
	public void insert(ResourceType o) { dao.insertBean(o); }

	@Override
	public void update(ResourceType o) { dao.updateBean(o); }

	@Override
	public void delete(ResourceType o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public ResourceType getById(int id) { return dao.getModelByPrimaryKey(id); }
	
	@Override
	public List<ResourceType> getAll() { return dao.getAllBeans(); }
}
