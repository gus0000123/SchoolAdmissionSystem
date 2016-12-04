package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.UserBO;
import com.mcit.kritth.dao.data.UserDAOImpl;
import com.mcit.kritth.model.data.User;

@Service("userService")
@Transactional
public class UserBOImpl implements UserBO
{
	@Autowired
	private UserDAOImpl dao;
	
	@Override
	public void insert(User o) { dao.insertBean(o); }

	@Override
	public void update(User o) { dao.updateBean(o); }

	@Override
	public void delete(User o) { dao.removeBeanByPrimaryKey(o.getUser()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public User getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<User> getAll() { return dao.getAllBeans(); }
}
