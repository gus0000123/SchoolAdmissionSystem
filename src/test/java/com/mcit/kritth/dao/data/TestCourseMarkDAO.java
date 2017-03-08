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

import com.mcit.kritth.model.data.CourseMark;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseMarkDAO
{
	@InjectMocks
	private CourseMarkDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private CourseMark coursemark;
	@Mock
	private List<CourseMark> coursemarklist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new CourseMarkDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(coursemark);
		verify(session).save(coursemark);
		
		dao.updateBean(coursemark);
		verify(session).saveOrUpdate(coursemark);
		
		Mockito.doReturn(0).when(coursemark).getCoursemark_id();
		Mockito.doReturn(coursemark).when(session).load(CourseMark.class, 0);
		dao.removeBeanByPrimaryKey(coursemark.getCoursemark_id());
		verify(session).delete(coursemark);
		
		Mockito.doReturn(coursemark).when(session).load(CourseMark.class, 0);
		assertTrue(dao.getModelByPrimaryKey(0) instanceof CourseMark);
	}
}
