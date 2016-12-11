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
import com.mcit.kritth.dao.template.StudentAdmissionStatusDAO;
import com.mcit.kritth.model.data.StudentAdmissionStatus;
import com.mcit.kritth.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestStudentAdmissionStatusBO implements TestService
{
	@Mock
	private StudentAdmissionStatus instance;
	@Mock
	private StudentAdmissionStatusDAO dao;
	@InjectMocks
	private StudentAdmissionStatusBOImpl service;
	
	@Before
	@Override
	public void init()
	{
		MockitoAnnotations.initMocks(this);
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
		service.update(instance);
		verify(dao).updateBean(instance);
	}

	@Test
	@Override
	public void testGet() {
		String id = TestUtil.generateRandomString();
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
		String id = TestUtil.generateRandomString();
		service.delete(instance);
		service.deleteById(id);
		when(instance.getStatus()).thenReturn(id);
		verify(dao).removeBeanByPrimaryKey(instance.getStatus());
		verify(dao).removeBeanByPrimaryKey(id);
	}
	
}
