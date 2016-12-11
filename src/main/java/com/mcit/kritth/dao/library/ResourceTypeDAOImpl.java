package com.mcit.kritth.dao.library;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.ResourceTypeDAO;
import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.model.library.ResourceType;

@SuppressWarnings("unchecked")
@Repository("resourceTypeDAO")
public class ResourceTypeDAOImpl extends HibernateSupport implements ResourceTypeDAO
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
