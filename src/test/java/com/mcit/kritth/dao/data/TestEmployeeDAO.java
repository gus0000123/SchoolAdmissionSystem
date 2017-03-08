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

import com.mcit.kritth.model.data.Employee;

@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeDAO
{
	@InjectMocks
	private EmployeeDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private Employee employee;
	@Mock
	private List<Employee> employeelist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new EmployeeDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(employee);
		verify(session).save(employee);
		
		dao.updateBean(employee);
		verify(session).saveOrUpdate(employee);
		
		Mockito.doReturn(0).when(employee).getId();
		Mockito.doReturn(employee).when(session).load(Employee.class, 0);
		dao.removeBeanByPrimaryKey(employee.getId());
		verify(session).delete(employee);
		
		Mockito.doReturn(employee).when(session).load(Employee.class, 0);
		assertTrue(dao.getModelByPrimaryKey(0) instanceof Employee);
	}
}
