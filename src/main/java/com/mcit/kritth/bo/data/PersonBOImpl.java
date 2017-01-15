package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.dao.template.PersonDAO;
import com.mcit.kritth.model.data.Person;

@Service("personService")
@Transactional
public class PersonBOImpl implements PersonBO
{
	@Autowired
	private PersonDAO dao;
	
	@Override
	public void insert(Person o) { dao.insertBean(o); }

	@Override
	public void update(Person o) { dao.updateBean(o); }

	@Override
	public void delete(Person o) { dao.removeBeanByPrimaryKey(o.getID()); }

	@Override
	public Person getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Person> getAll() { return dao.getAllBeans(); }
}
