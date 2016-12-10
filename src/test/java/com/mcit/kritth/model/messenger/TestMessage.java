package com.mcit.kritth.model.messenger;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.util.TestUtil;

public class TestMessage implements TestBean
{
	private Message m;
	private int message_id;
	private Person sender;
	private Date creation_time;
	private String headline;
	private String message;
	
	@Before
	@Override
	public void init()
	{
		m = new Message();
		message_id = TestUtil.generateRandomNumber();
		sender = new Person();
		creation_time = new Date();
		headline = TestUtil.generateRandomString();
		message = TestUtil.generateRandomString();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		m.setId(message_id);
		m.setSender(sender);
		m.setCreation_time(creation_time);
		m.setHeadline(headline);
		m.setMessage(message);
		
		assertNotNull(m);
		assertNotNull(m.getId());
		assertNotNull(m.getSender());
		assertNotNull(m.getCreation_time());
		assertNotNull(m.getHeadline());
		assertNotNull(m.getMessage());
		assertNotNull(m.toString());
	}
}
