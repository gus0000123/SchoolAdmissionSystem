package com.mcit.kritth.dao.data;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mcit.kritth.model.data.Person;

@RunWith(MockitoJUnitRunner.class)
public class TestPersonDAO
{
	@InjectMocks
	private PersonDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private Person person;
	@Mock
	private List<Person> personlist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new PersonDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(person);
		verify(session).save(person);
		
		dao.updateBean(person);
		verify(session).saveOrUpdate(person);
		
		Mockito.doReturn(0).when(person).getID();
		Mockito.doReturn(person).when(session).load(Person.class, 0);
		dao.removeBeanByPrimaryKey(person.getID());
		verify(session).delete(person);
		
		Mockito.doReturn(person).when(session).load(Person.class, 0);
		assertTrue(dao.getModelByPrimaryKey(0) instanceof Person);
	}
}
