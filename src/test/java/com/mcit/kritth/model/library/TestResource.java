package com.mcit.kritth.model.library;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestResource implements TestBean
{
	private Resource r;
	private int id;
	private String title;
	private String subtitle;
	private String ISBN;
	private String description;
	private int page;
	private boolean availability;
	private Date published_date;
	private Date date_added;
	private Author author;
	private ResourceType type;
	private Set<Author> contributor;
	
	@Before
	@Override
	public void init()
	{
		r = new Resource();
		id = TestUtil.generateRandomNumber();
		title = TestUtil.generateRandomString();
		subtitle = TestUtil.generateRandomString();
		ISBN = TestUtil.generateRandomString();
		description = TestUtil.generateRandomString();
		page = TestUtil.generateRandomNumber();
		availability = true;
		published_date = new Date();
		date_added = new Date();
		author = new Author();
		type = new ResourceType();
		contributor = new HashSet<>();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		r.setId(id);
		r.setTitle(title);
		r.setSubtitle(subtitle);
		r.setISBN(ISBN);
		r.setDescription(description);
		r.setPages(page);
		r.setAvailability(availability);
		r.setPublished_date(published_date);
		r.setDate_added(date_added);
		r.setAuthor(author);
		r.setType(type);
		r.setContributor(contributor);
		
		assertNotNull(r);
		assertNotNull(r.getId());
		assertNotNull(r.getTitle());
		assertNotNull(r.getSubtitle());
		assertNotNull(r.getISBN());
		assertNotNull(r.getDescription());
		assertNotNull(r.getPages());
		assertNotNull(r.isAvailability());
		assertNotNull(r.getPublished_date());
		assertNotNull(r.getDate_added());
		assertNotNull(r.getAuthor());
		assertNotNull(r.getType());
		assertNotNull(r.getContributor());
		assertNotNull(r.toString());
		
		r = new Resource();
		
		r.getContributor().add(new Author());
		
		assertNotNull(r.getContributor());
	}
}
