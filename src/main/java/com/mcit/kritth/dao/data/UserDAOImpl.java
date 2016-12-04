package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.User;

@SuppressWarnings("unchecked")
@Repository("userDAO")
public class UserDAOImpl extends HibernateSupport implements DAO<User>
{
	@Override
	public void insertBean(User o) { insert(o); }

	@Override
	public void updateBean(User o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(User.class, id); }
	
	@Override
	public List<User> getAllBeans() { return (List<User>) loadAll(User.class); }

	@Override
	public User getModelByPrimaryKey(Serializable id) { return (User) load(User.class, id); }
}
