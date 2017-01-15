package com.mcit.kritth.bo.library;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.dao.template.ResourceTypeDAO;
import com.mcit.kritth.model.library.ResourceType;
import com.mcit.kritth.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestResourceTypeBO implements TestService
{
	@Mock
	private ResourceType instance;
	@Mock
	private ResourceTypeDAO dao;
	@InjectMocks
	private ResourceTypeBOImpl service;
	
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
		when(instance.getName()).thenReturn(id);
		verify(dao).removeBeanByPrimaryKey(instance.getName());
	}
	
}
