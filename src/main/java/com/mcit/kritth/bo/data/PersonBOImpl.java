package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.PersonDAOImpl;
import com.mcit.kritth.model.data.Person;

@Service("personService")
@Transactional
public class PersonBOImpl implements BO<Person>
{
	@Autowired
	private PersonDAOImpl dao;
	
	@Override
	public void insert(Person o) { dao.insertBean(o); }

	@Override
	public void update(Person o) { dao.updateBean(o); }

	@Override
	public void delete(Person o) { dao.removeBeanByPrimaryKey(o.getID()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Person getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Person> getAll() { return dao.getAllBeans(); }
}
