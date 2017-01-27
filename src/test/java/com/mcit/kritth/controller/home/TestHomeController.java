package com.mcit.kritth.controller.home;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.HttpSessionRequiredException;

import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;

@RunWith(MockitoJUnitRunner.class)
public class TestHomeController
{
	@InjectMocks
	private HomeController controller;
	
	@Mock
	private User user;
	
	@Mock
	private Person person;
	
	private MockMvc mockMvc;
	
	@Before
	public void Init()
	{
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testForwarding() throws Exception
	{
		when(user.getPerson()).thenReturn(person);
		// Test tab null
		this.mockMvc.perform(post("/home")
				.sessionAttr("user", user))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/homeOverview"));
		// Test tab overview
		this.mockMvc.perform(post("/home")
				.sessionAttr("user", user)
				.param("tab", "overview"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/homeOverview"));
		// Test tab info
		this.mockMvc.perform(post("/home")
				.sessionAttr("user", user)
				.param("tab", "info"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/homeInfo"));
		// Test tab message
		this.mockMvc.perform(post("/home")
				.sessionAttr("user", user)
				.param("tab", "message"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/homeMessage"));
		// Test tab application
		this.mockMvc.perform(post("/home")
				.sessionAttr("user", user)
				.param("tab", "application"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/homeApplication"));
		// Test tab default redirect
		this.mockMvc.perform(post("/home")
				.sessionAttr("user", user)
				.param("tab", "whatever"))
				.andExpect(status().is3xxRedirection());
		
		when(user.getPerson()).thenReturn(null);
		// Test person null
		this.mockMvc.perform(post("/home")
				.sessionAttr("user", user))
				.andExpect(status().is3xxRedirection());
	}
	
	@Test(expected = HttpSessionRequiredException.class)
	public void testNoSessionAttribute() throws Exception
	{
		// Test no session attribute
		this.mockMvc.perform(post("/home"))
			.andExpect(status().is5xxServerError());
	}
}
