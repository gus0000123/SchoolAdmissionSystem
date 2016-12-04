package com.mcit.kritth.dao.library;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.library.ResourceType;

@SuppressWarnings("unchecked")
@Repository("resourceTypeDAO")
public class ResourceTypeDAOImpl extends HibernateSupport implements DAO<ResourceType>
{
	@Override
	public void insertBean(ResourceType o) { insert(o); }

	@Override
	public void updateBean(ResourceType o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(ResourceType.class, id); }

	@Override
	public List<ResourceType> getAllBeans() { return (List<ResourceType>) loadAll(ResourceType.class); }

	@Override
	public ResourceType getModelByPrimaryKey(Serializable id) { return (ResourceType) load(ResourceType.class, id); }
}
