package com.mcit.kritth.dao.data;

import static org.junit.Assert.assertEquals;
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
import com.mcit.kritth.model.data.User;

@RunWith(MockitoJUnitRunner.class)
public class TestUserDAO
{
	@InjectMocks
	private UserDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private User user;
	@Mock
	private Person person;
	@Mock
	private List<User> userlist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new UserDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(user);
		verify(session).save(user);
		
		dao.updateBean(user);
		verify(session).saveOrUpdate(user);
		
		Mockito.doReturn("").when(user).getUsername();
		Mockito.doReturn(user).when(session).load(User.class, "");
		dao.removeBeanByPrimaryKey(user.getUsername());
		verify(session).delete(user);
		
		Mockito.doReturn(user).when(session).load(User.class, "");
		assertTrue(dao.getModelByPrimaryKey("") instanceof User);
	}
}
