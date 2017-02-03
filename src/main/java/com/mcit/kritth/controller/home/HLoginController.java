package com.mcit.kritth.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.UserBO;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;

@Controller
@SessionAttributes("user")
public class HLoginController
{
	@Autowired
	private UserBO service;
	@Autowired
	private PersonBO pservice;
	
	@RequestMapping(value = "/login", method= {RequestMethod.POST})
	public  ModelAndView login(
			@ModelAttribute("attemptLogin") User attempt)
	{
		User u = service.getById(attempt.getUsername());
		
		ModelAndView model;
		
		if (u != null)
		{
			if (u.getPassword().equals(attempt.getPassword()))
			{
				model = new ModelAndView("forward:/home");
				model.addObject("user", u);
			}
			else
			{
				model = new ModelAndView("index");
				model.addObject("error", "password");
				model.addObject("attempt", new User());
				model.addObject("register_person", new Person());
			}
		}
		else
		{
			model = new ModelAndView("index");
			model.addObject("error", "user");
			model.addObject("attempt", new User());
			model.addObject("register_person", new Person());
		}
		
		return model;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(
			WebRequest request,
			SessionStatus status)
	{
		status.setComplete();
		request.removeAttribute("user", WebRequest.SCOPE_SESSION);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/registerUser", method = {RequestMethod.POST})
	public ModelAndView registerUser(
			@ModelAttribute("register_person") Person person,
			@RequestParam("password") String password,
			@RequestParam("cpassword") String cpassword)
	{
		ModelAndView model = new ModelAndView("index");
		
		User attempt;
		
		if (password.equals(cpassword))
		{
			
			// Create person
			pservice.insert(person);
						
			// Create user
			User u = new User();
			u.setPerson(person);
			u.setPassword(password);
			u.setUsername(service.getNewUsername(person));
			service.insert(u);
			
			attempt = u;
			
			model.addObject("page", "confirmation");
			model.addObject("account_name", u.getUsername());
		}
		else
		{
			attempt = new User();
			
			model.addObject("page", "register");
			model.addObject("error", "password");
		}
		
		model.addObject("attempt", attempt);
		model.addObject("register_person", new Person());
		
		return model;
	}
}
