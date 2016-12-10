package com.mcit.kritth.model.library;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestResourceType implements TestBean
{
	private ResourceType r;
	private String name;
	private String description;
	
	@Before
	@Override
	public void init()
	{
		r = new ResourceType();
		name = TestUtil.generateRandomString();
		description = TestUtil.generateRandomString();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		r.setName(name);
		r.setDescription(description);
		
		assertNotNull(r);
		assertNotNull(r.getName());
		assertNotNull(r.getDescription());
		assertNotNull(r.toString());
	}
}
