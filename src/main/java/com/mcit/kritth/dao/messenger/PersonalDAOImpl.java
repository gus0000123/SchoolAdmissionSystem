package com.mcit.kritth.dao.messenger;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.messenger.Personal;

@SuppressWarnings("unchecked")
@Repository("personalDAO")
public class PersonalDAOImpl extends HibernateSupport implements DAO<Personal>
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
	
	public List<Personal> getAllByReceiverId(int id)
	{
		return getCondition(CONDITION_EQUAL, 0, Personal.class, "receiver_id", new Integer(id));
	}
	public List<Personal> getAllBySenderId(int id)
	{
		return getCondition(CONDITION_EQUAL, 0, Personal.class, "sender_ID", new Integer(id));
	}
}
