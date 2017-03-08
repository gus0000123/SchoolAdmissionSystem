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

import com.mcit.kritth.model.data.Student;

@RunWith(MockitoJUnitRunner.class)
public class TestStudentDAO
{
	@InjectMocks
	private StudentDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private Student student;
	@Mock
	private List<Student> studentlist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new StudentDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(student);
		verify(session).save(student);
		
		dao.updateBean(student);
		verify(session).saveOrUpdate(student);
		
		Mockito.doReturn(0).when(student).getId();
		Mockito.doReturn(student).when(session).load(Student.class, 0);
		dao.removeBeanByPrimaryKey(student.getId());
		verify(session).delete(student);
		
		Mockito.doReturn(student).when(session).load(Student.class, 0);
		assertTrue(dao.getModelByPrimaryKey(0) instanceof Student);
	}
}
