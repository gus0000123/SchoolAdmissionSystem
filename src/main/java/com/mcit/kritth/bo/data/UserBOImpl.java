package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.UserDAOImpl;
import com.mcit.kritth.model.data.User;

@Service("userService")
@Transactional
public class UserBOImpl implements BO<User>
{
	@Autowired
	private UserDAOImpl dao;
	
	@Override
	public void insert(User o) { dao.insertBean(o); }

	@Override
	public void update(User o) { dao.updateBean(o); }

	@Override
	public void delete(User o) { dao.removeBeanByPrimaryKey(o.getUser_id()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public User getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<User> getAll() { return dao.getAllBeans(); }
}
