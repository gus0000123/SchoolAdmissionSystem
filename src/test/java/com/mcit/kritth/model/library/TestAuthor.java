package com.mcit.kritth.model.library;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestAuthor implements TestBean
{
	private Author a;
	private int id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String bibliography;
	private String note;
	private Set<Resource> resources;
	
	@Before
	@Override
	public void init()
	{
		a = new Author();
		id = TestUtil.generateRandomNumber();
		first_name = TestUtil.generateRandomString();
		middle_name = TestUtil.generateRandomString();
		last_name = TestUtil.generateRandomString();
		bibliography = TestUtil.generateRandomString();
		note = TestUtil.generateRandomString();
		resources = new HashSet<>();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		a.setId(id);
		a.setFirst_name(first_name);
		a.setMiddle_name(middle_name);
		a.setLast_name(last_name);
		a.setBibliography(bibliography);
		a.setNote(note);
		a.setResources(resources);
		
		assertNotNull(a);
		assertNotNull(a.getId());
		assertNotNull(a.getFirst_name());
		assertNotNull(a.getMiddle_name());
		assertNotNull(a.getLast_name());
		assertNotNull(a.getBibliography());
		assertNotNull(a.getNote());
		assertNotNull(a.getResources());
		assertNotNull(a.toString());
		
		a = new Author();
		
		a.getResources().add(new Resource());
		
		assertNotNull(a.getResources());
	}
}
