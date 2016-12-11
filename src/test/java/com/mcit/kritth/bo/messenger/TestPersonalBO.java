package com.mcit.kritth.bo.messenger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.dao.template.PersonalDAO;
import com.mcit.kritth.model.messenger.Personal;
import com.mcit.kritth.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestPersonalBO implements TestService
{
	@Mock
	private Personal instance;
	@Mock
	private PersonalDAO dao;
	@InjectMocks
	private PersonalBOImpl service;
	
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
		int id = TestUtil.generateRandomNumber();
		service.getById(id);
		verify(dao).getModelByPrimaryKey(id);
		
		id = TestUtil.generateRandomNumber();
		List<Personal> receiverList = new ArrayList<>();
		receiverList.add(instance);
		when(dao.getAllByReceiverId(id)).thenReturn(receiverList);
		when(instance.isTrash()).thenReturn(false);
		service.getAllFromReceiverId(id);
		verify(dao).getAllByReceiverId(id);
		
		id = TestUtil.generateRandomNumber();
		List<Personal> senderList = new ArrayList<>();
		senderList.add(instance);
		when(dao.getAllBySenderId(id)).thenReturn(senderList);
		service.getAllFromSenderId(id);
		verify(dao).getAllBySenderId(id);
		
		id = TestUtil.generateRandomNumber();
		List<Personal> trashList = new ArrayList<>();
		trashList.add(instance);
		when(instance.isTrash()).thenReturn(true);
		when(dao.getAllByReceiverId(id)).thenReturn(trashList);
		service.getTrashFromReceiverId(id);
		verify(dao).getAllByReceiverId(id);
		
		// Test if else branch
		id = TestUtil.generateRandomNumber();
		senderList = new ArrayList<>();
		while (senderList.size() < 50) { senderList.add(new Personal()); }
		when(dao.getAllBySenderId(id)).thenReturn(senderList);
		service.getAllFromSenderId(id);
		verify(dao).getAllBySenderId(id);
		
		id = TestUtil.generateRandomNumber();
		trashList = new ArrayList<>();
		trashList.add(instance);
		while (trashList.size() < 50) {
			Personal pm = new Personal();
			pm.setTrash(true);
			trashList.add(pm);
		}
		when(dao.getAllByReceiverId(id)).thenReturn(trashList);
		service.getTrashFromReceiverId(id);
		verify(dao).getAllByReceiverId(id);
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
		service.delete(instance);
		service.deleteById(id);
		when(instance.getId()).thenReturn(id);
		verify(dao).removeBeanByPrimaryKey(instance.getId());
		verify(dao).removeBeanByPrimaryKey(id);
	}
	
}
