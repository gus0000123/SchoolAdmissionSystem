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
import com.mcit.kritth.dao.template.CourseWorkDAO;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseWorkBO implements TestService
{
	@Mock
	private CourseWork instance;
	@Mock
	private CourseWorkDAO dao;
	@InjectMocks
	private CourseWorkBOImpl service;
	
	@Before
	@Override
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Override
	public void testInsert()
	{/*
		service.insert(instance);
		verify(dao).insertBean(instance);
*/	}

	@Test
	@Override
	public void testUpdate() {
/*		service.update(instance);
		verify(dao).updateBean(instance);
*/	}

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
/*		int id = TestUtil.generateRandomNumber();
		when(instance.getCoursework_id()).thenReturn(id);
		service.delete(instance);
		verify(dao).removeBeanByPrimaryKey(instance.getCoursework_id());
*/	}
	
}
