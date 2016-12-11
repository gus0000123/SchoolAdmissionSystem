package com.mcit.kritth.dao.messenger;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.PersonalDAO;
import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.model.messenger.Personal;

@SuppressWarnings("unchecked")
@Repository("personalDAO")
public class PersonalDAOImpl extends HibernateSupport implements PersonalDAO
{
	@Override
	public void insertBean(Personal o) { insert(o); }

	@Override
	public void updateBean(Personal o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Personal.class, id); }
	
	@Override
	public List<Personal> getAllBeans() { return (List<Personal>) loadAll(Personal.class); }

	@Override
	public Personal getModelByPrimaryKey(Serializable id) { return (Personal) load(Personal.class, id);	}
	
	@Override
	public List<Personal> getAllByReceiverId(Serializable id)
	{
		return getCondition(CONDITION_EQUAL, 0, Personal.class, "receiver_id", (int) id);
	}
	
	@Override
	public List<Personal> getAllBySenderId(Serializable id)
	{
		return getCondition(CONDITION_EQUAL, 0, Personal.class, "sender_ID", (int) id);
	}
}
