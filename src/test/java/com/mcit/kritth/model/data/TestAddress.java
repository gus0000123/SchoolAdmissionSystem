package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestAddress implements TestBean
{
	private Address address;
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
}
