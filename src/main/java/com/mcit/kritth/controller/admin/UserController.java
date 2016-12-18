package com.mcit.kritth.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController
{
	@RequestMapping(value = "/userController")
	public ModelAndView userActionSelector()
	{
		String url = "forward:/studentController";
		
		// TODO: insert logic
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value = "/userDoInsert")
	public ModelAndView redirectInsertUser()
	{
		
		return null;
	}
	
	@RequestMapping(value = "/userDoUpdate")
	public ModelAndView redirectUpdateUser()
	{
		return null;
	}
}
