package com.mcit.kritth.dao.data;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

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

import com.mcit.kritth.model.Bean;
import com.mcit.kritth.model.data.Course;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseDAO
{
	@InjectMocks
	private CourseDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private Course course;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new CourseDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(course);
		verify(session).save(course);
		
		dao.updateBean(course);
		verify(session).saveOrUpdate(course);
		
		Mockito.doReturn("").when(course).getCourse_code();
		Mockito.doReturn(course).when(session).load(Course.class, "");
		dao.removeBeanByPrimaryKey(course.getCourse_code());
		verify(session).delete(course);
		
		Mockito.doReturn(course).when(session).load(Course.class, "");
		assertTrue(dao.load(Course.class, "") instanceof Course);
	}
}
