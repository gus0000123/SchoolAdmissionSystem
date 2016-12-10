package com.mcit.kritth.model.messenger;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.util.TestUtil;

public class TestSystemGroup implements TestBean
{
	private SystemGroup s;
	private int sys_group_id;
	private String name;
	private Set<Person> recipients;
	
	@Before
	@Override
	public void init()
	{
		s = new SystemGroup();
		sys_group_id = TestUtil.generateRandomNumber();
		name = TestUtil.generateRandomString();
		recipients = new HashSet<>();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		s.setId(sys_group_id);
		s.setName(name);
		s.setRecipients(recipients);
		
		assertNotNull(s);
		assertNotNull(s.getId());
		assertNotNull(s.getName());
		assertNotNull(s.getRecipients());
		
		s = new SystemGroup();
		
		s.getRecipients().add(new Person());
		
		assertNotNull(s.getRecipients());
	}
}
