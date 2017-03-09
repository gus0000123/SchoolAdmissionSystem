package com.mcit.kritth.bo.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.dao.template.StudentDAO;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestStudentBO implements TestService
{
	@Mock
	private Student instance;
	@Mock
	private Department d;
	@Mock
	private StudentDAO dao;
	@InjectMocks
	private StudentBOImpl service;
	
	@Before
	@Override
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		instance.setDepartment(d);
	}

	@Test
	@Override
	public void testInsert()
	{
		service.insert(instance);
		verify(dao).insertBean(instance);
	}

	@Test
	@Override
	public void testUpdate() {
	}

	@Test
	@Override
	public void testGet() {
		int id = TestUtil.generateRandomNumber();
		service.getById(id);
		verify(dao).getModelByPrimaryKey(id);
	}

	@Test
	@Override
	public void testGetAll() {
		service.getAll();
		verify(dao).getAllBeans();
	}

	@Test
	@Override
	public void testDelete() {
		int id = TestUtil.generateRandomNumber();
		when(instance.getId()).thenReturn(id);
		when(instance.getDepartment()).thenReturn(d);
		service.delete(instance);
		verify(dao).removeBeanByPrimaryKey(instance.getId());
	}
	
}
