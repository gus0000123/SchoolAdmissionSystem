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

import com.mcit.kritth.model.data.StudentAdmissionStatus;

@RunWith(MockitoJUnitRunner.class)
public class TestStudentAdmissionStatusDAO
{
	@InjectMocks
	private StudentAdmissionStatusDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private StudentAdmissionStatus admission_status;
	@Mock
	private List<StudentAdmissionStatus> admission_statuslist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new StudentAdmissionStatusDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(admission_status);
		verify(session).save(admission_status);
		
		dao.updateBean(admission_status);
		verify(session).saveOrUpdate(admission_status);
		
		Mockito.doReturn("").when(admission_status).getStatus();
		Mockito.doReturn(admission_status).when(session).load(StudentAdmissionStatus.class, "");
		dao.removeBeanByPrimaryKey(admission_status.getStatus());
		verify(session).delete(admission_status);
		
		Mockito.doReturn(admission_status).when(session).load(StudentAdmissionStatus.class, "");
		assertTrue(dao.getModelByPrimaryKey("") instanceof StudentAdmissionStatus);
	}
}
