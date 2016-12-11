package com.mcit.kritth.dao.messenger;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.SystemGroupDAO;
import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.model.messenger.SystemGroup;

@SuppressWarnings("unchecked")
@Repository("systemGroupDAO")
public class SystemGroupDAOImpl extends HibernateSupport implements SystemGroupDAO
{
	@Override
	public void insertBean(SystemGroup o) { insert(o); }

	@Override
	public void updateBean(SystemGroup o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(SystemGroup.class, id); }

	@Override
	public List<SystemGroup> getAllBeans() { return (List<SystemGroup>) loadAll(SystemGroup.class); }

	@Override
	public SystemGroup getModelByPrimaryKey(Serializable id) { return (SystemGroup) load(SystemGroup.class, id); }
}
