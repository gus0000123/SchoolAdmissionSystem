package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestPerson implements TestBean
{
	private Person p;
	private int ID;
	private String firstName;
	private String middleName;
	private String lastName;
	private Address address;
	private String telNo;
	private String email;
	private char gender;
	private String sin;
	
	@Before
	@Override
	public void init()
	{
		p = new Person();
		ID = TestUtil.generateRandomNumber();
		firstName = TestUtil.generateRandomString();
		middleName = TestUtil.generateRandomString();
		lastName = TestUtil.generateRandomString();
		address = new Address();
		telNo = TestUtil.generateRandomString();
		email = TestUtil.generateRandomString();
		gender = TestUtil.generateRandomString().charAt(0);
		sin = TestUtil.generateRandomString();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		p.setID(ID);
		p.setFirstName(firstName);
		p.setMiddleName(middleName);
		p.setLastName(lastName);
		p.setAddress(address);
		p.setTelNo(telNo);
		p.setEmail(email);
		p.setGender(gender);
		p.setSin(sin);
		
		assertNotNull(p);
		assertNotNull(p.getID());
		assertNotNull(p.getFirstName());
		assertNotNull(p.getMiddleName());
		assertNotNull(p.getLastName());
		assertNotNull(p.getAddress());
		assertNotNull(p.getTelNo());
		assertNotNull(p.getEmail());
		assertNotNull(p.getGender());
		assertNotNull(p.getSin());
		assertNotNull(p.toString());
	}
	
}
