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

import com.mcit.kritth.model.data.DepartmentCode;

@RunWith(MockitoJUnitRunner.class)
public class TestDepartmentCodeDAO
{
	@InjectMocks
	private DepartmentCodeDAOImpl dao;
	@Mock
	private SessionFactory factory;
	@Mock
	private Session session;
	@Mock
	private DepartmentCode departmentcode;
	@Mock
	private List<DepartmentCode> departmentcodelist;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		dao = new DepartmentCodeDAOImpl();
		dao.setSessionFactory(factory);
		Mockito.doReturn(session).when(factory).getCurrentSession();
	}
	
	@Test
	public void testBasic()
	{
		dao.insertBean(departmentcode);
		verify(session).save(departmentcode);
		
		dao.updateBean(departmentcode);
		verify(session).saveOrUpdate(departmentcode);
		
		Mockito.doReturn("").when(departmentcode).getDept_code();
		Mockito.doReturn(departmentcode).when(session).load(DepartmentCode.class, "");
		dao.removeBeanByPrimaryKey(departmentcode.getDept_code());
		verify(session).delete(departmentcode);
		
		Mockito.doReturn(departmentcode).when(session).load(DepartmentCode.class, "");
		assertTrue(dao.getModelByPrimaryKey("") instanceof DepartmentCode);
	}
}
