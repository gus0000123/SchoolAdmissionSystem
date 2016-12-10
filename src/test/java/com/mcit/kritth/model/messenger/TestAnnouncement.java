package com.mcit.kritth.model.messenger;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;

public class TestAnnouncement implements TestBean
{
	private Announcement a;
	private SystemGroup system_group;
	
	@Before
	@Override
	public void init()
	{
		a = new Announcement();
		system_group = new SystemGroup();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		a.setSystem_group(system_group);
		
		assertNotNull(a);
		assertNotNull(a.getSystem_group());
		assertNotNull(a.toString());
	}
}
