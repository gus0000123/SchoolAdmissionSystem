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

import com.mcit.kritth.model.data.Department;

@RunWith(MockitoJUnitRunner.class)
public class TestDepartmentDAO
{
	@InjectMocks
	private DepartmentDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private Department department;
	@Mock
	private List<Department> departmentlist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new DepartmentDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(department);
		verify(session).save(department);
		
		dao.updateBean(department);
		verify(session).saveOrUpdate(department);
		
		Mockito.doReturn(0).when(department).getDeptId();
		Mockito.doReturn(department).when(session).load(Department.class, 0);
		dao.removeBeanByPrimaryKey(department.getDeptId());
		verify(session).delete(department);
		
		Mockito.doReturn(department).when(session).load(Department.class, 0);
		assertTrue(dao.getModelByPrimaryKey(0) instanceof Department);
	}
}
