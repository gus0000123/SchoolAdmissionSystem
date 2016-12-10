package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.model.messenger.SystemGroup;
import com.mcit.kritth.util.TestUtil;

public class TestSchedule implements TestBean
{
	private Schedule s;
	private int id;
	private Date start_time;
	private Date end_time;
	private String headline;
	private String detail;
	private Person creator;
	private Set<SystemGroup> groupToShow;
	
	@Before
	@Override
	public void init()
	{
		s = new Schedule();
		id = TestUtil.generateRandomNumber();
		start_time = new Date();
		end_time = new Date();
		headline = TestUtil.generateRandomString();
		detail = TestUtil.generateRandomString();
		creator = new Person();
		groupToShow = new HashSet<>();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		s.setId(id);
		s.setStart_time(start_time);
		s.setEnd_time(end_time);
		s.setHeadline(headline);
		s.setDetail(detail);
		s.setCreator(creator);
		s.setGroupToShow(groupToShow);
		
		assertNotNull(s);
		assertNotNull(s.getId());
		assertNotNull(s.getStart_time());
		assertNotNull(s.getEnd_time());
		assertNotNull(s.getHeadline());
		assertNotNull(s.getDetail());
		assertNotNull(s.getCreator());
		assertNotNull(s.getGroupToShow());
		assertNotNull(s.toString());
		
		s = new Schedule();
		
		s.getGroupToShow().add(new SystemGroup());
		
		assertNotNull(s.getGroupToShow());
	}

}
