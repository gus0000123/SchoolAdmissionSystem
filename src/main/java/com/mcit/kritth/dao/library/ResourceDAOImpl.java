package com.mcit.kritth.dao.library;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.library.Resource;

@SuppressWarnings("unchecked")
@Repository("resourceDAO")
public class ResourceDAOImpl extends HibernateSupport implements DAO<Resource>
{
	@Override
	public void insertBean(Resource o) { insert(o); }

	@Override
	public void updateBean(Resource o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Resource.class, id); }

	@Override
	public List<Resource> getAllBeans() { return (List<Resource>) loadAll(Resource.class); }

	@Override
	public Resource getModelByPrimaryKey(Serializable id) { return (Resource) load(Resource.class, id); }
}
