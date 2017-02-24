package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.bo.template.UserBO;
import com.mcit.kritth.dao.template.PersonDAO;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;

@Service("personService")
@Transactional
public class PersonBOImpl implements PersonBO
{
	@Autowired
	private PersonDAO dao;
	
	@Autowired
	private UserBO uservice;
	
	@Autowired
	private StudentBO sservice;
	
	@Autowired
	private EmployeeBO eservice;
	
	@Override
	public void insert(Person o) { dao.insertBean(o); }

	@Override
	public void update(Person o) { dao.updateBean(o); }

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public void delete(Person o)
	{
		try 
		{
			User user = uservice.getByPersonId(o.getID());
			uservice.delete(user);
		}
		catch (Exception ex) { ex.printStackTrace(); }
		
		try 
		{
			Student student = sservice.getById(o.getID());
			sservice.delete(student);
		}
		catch (Exception ex) { ex.printStackTrace(); }
		
		try
		{
			Employee e = eservice.getById(o.getID());
			eservice.delete(e);
		}
		catch (Exception ex) { ex.printStackTrace(); }
		
		dao.removeBeanByPrimaryKey(o.getID());
	}

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public Person getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Person> getAll() { return dao.getAllBeans(); }
}
