package com.mcit.kritth.dao.template;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mcit.kritth.model.Bean;

/***
 * Incomplete Hibernate Support test
 * @author Best
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TestHibernateSupport
{
	@InjectMocks
	private HibernateSupport hs;
	@Mock
	private Bean bean;
	@Mock
	private List<Bean> beanList;
	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;
	@Mock
	private Query<?> query;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		hs = new HibernateSupport();
		hs.setSessionFactory(sessionFactory);
		Mockito.doReturn(session).when(sessionFactory).getCurrentSession();
	}
	
	@Test
	public void testInsert()
	{
		hs.insert(bean);
		verify(session).save(bean);
	}
	
	@Test
	public void testUpdate()
	{
		hs.update(bean);
		verify(session).saveOrUpdate(bean);
	}
	
	@Test
	public void testDelete()
	{
		Mockito.doReturn(bean).when(session).load(Bean.class, "");
		hs.deleteById(Bean.class, "");
		verify(session).delete(bean);
	}
	
	@Test
	public void testLoad()
	{
		Mockito.doReturn(bean).when(session).load(Bean.class, "");
		assertTrue(hs.load(Bean.class, "") instanceof Bean);
	}
}