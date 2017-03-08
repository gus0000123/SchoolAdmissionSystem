package com.mcit.kritth.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestAddress implements TestBean
{
	private Address address;
	private Address address2;
	private String city;
	private String street;
	private String state;
	private String postal;
	private String country;
	
	@Before
	@Override
	public void init()
	{
		address = new Address();
		
		// Set up variable
		city = TestUtil.generateRandomString(); 
		street = TestUtil.generateRandomString();
		state = TestUtil.generateRandomString();
		postal = TestUtil.generateRandomString();
		country = TestUtil.generateRandomString();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		// Setter
		address.setCity(city);
		address.setStreetAddress(street);
		address.setState(state);
		address.setPostal(postal);
		address.setCountry(country);
		
		// Check null
		assertNotNull(address);
		assertNotNull(address.getCity());
		assertNotNull(address.getStreetAddress());
		assertNotNull(address.getState());
		assertNotNull(address.getPostal());
		assertNotNull(address.getCountry());
		assertNotNull(address.toString());
	}
	
	@Test
	public void testCopyAndEqual()
	{
		address2 = new Address();
		Course course = new Course();
		Address address3 = new Address();
		address3.setCity("c");
		address3.setCountry("");
		address3.setPostal("");
		address3.setState("");
		address3.setStreetAddress("");
		address2.copy(address);
		address.copy(course);
		assertTrue(address.equals(address2));
		assertFalse(address.equals(course));
		assertFalse(address.equals(address3));
		assertEquals(address, address2);
	}
}
