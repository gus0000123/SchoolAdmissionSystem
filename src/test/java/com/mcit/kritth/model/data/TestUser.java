package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestUser implements TestBean
{
	private User u;
	private String username;
	private String password;
	private int authority;
	private Person person;
	
	@Before
	@Override
	public void init()
	{
		u = new User();
		username = TestUtil.generateRandomString();
		password = TestUtil.generateRandomString();
		authority = TestUtil.generateRandomNumber(9);
		person = new Person();
		person.setAddress(new Address());
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		u.setUsername(username);
		u.setPassword(password);
		u.setAuthority(authority);
		u.setPerson(person);
		
		assertNotNull(u);
		assertNotNull(u.getUsername());
		assertNotNull(u.getPassword());
		assertNotNull(u.getAuthority());
		assertNotNull(u.getPerson());
		assertNotNull(u.toString());
	}
}