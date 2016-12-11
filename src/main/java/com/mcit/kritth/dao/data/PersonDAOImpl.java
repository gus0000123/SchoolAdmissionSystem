package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.dao.template.PersonDAO;
import com.mcit.kritth.model.data.Person;

@SuppressWarnings("unchecked")
@Repository("personDAO")
public class PersonDAOImpl extends HibernateSupport implements PersonDAO
{
	@Override
	public void insertBean(Person o) { insert(o); }

	@Override
	public void updateBean(Person o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Person.class, id); }

	@Override
	public List<Person> getAllBeans() { return loadAll(Person.class); }

	@Override
	public Person getModelByPrimaryKey(Serializable id) { return (Person) load(Person.class, id); }
}
