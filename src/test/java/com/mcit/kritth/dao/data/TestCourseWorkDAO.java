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

import com.mcit.kritth.model.data.CourseWork;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseWorkDAO
{
	@InjectMocks
	private CourseWorkDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private CourseWork coursework;
	@Mock
	private List<CourseWork> courseworklist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new CourseWorkDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(coursework);
		verify(session).save(coursework);
		
		dao.updateBean(coursework);
		verify(session).saveOrUpdate(coursework);
		
		Mockito.doReturn(0).when(coursework).getCoursework_id();
		Mockito.doReturn(coursework).when(session).load(CourseWork.class, 0);
		dao.removeBeanByPrimaryKey(coursework.getCoursework_id());
		verify(session).delete(coursework);
		
		Mockito.doReturn(coursework).when(session).load(CourseWork.class, 0);
		assertTrue(dao.getModelByPrimaryKey(0) instanceof CourseWork);
	}
}
