package com.mcit.kritth.model.messenger;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.model.data.Person;

public class TestPersonal implements TestBean
{
	private Personal p;
	private Person receiver;
	private boolean important;
	private boolean trash;
	
	@Before
	@Override
	public void init()
	{
		p = new Personal();
		receiver = new Person();
		important = true;
		trash = true;
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		p.setReceiver(receiver);
		p.setImportant(important);
		p.setTrash(trash);
		
		assertNotNull(p);
		assertNotNull(p.getReceiver());
		assertNotNull(p.isImportant());
		assertNotNull(p.isTrash());
		assertNotNull(p.toString());
	}
}