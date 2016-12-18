package com.mcit.kritth.controller;

import org.springframework.stereotype.Controller;
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
import com.mcit.kritth.spring.ApplicationContextProvider;
import com.mcit.kritth.util.BeanUtil;

@Controller
@SessionAttributes("user")
public class LoginController
{
	@RequestMapping(value = "/login", method= {RequestMethod.POST})
	public  ModelAndView login(
			@RequestParam("user") String user,
			@RequestParam("password") String password)
	{
		UserBO service = ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class);
		User u = service.getById(user);
		
		ModelAndView model;
		
		if (u != null)
		{
			if (u.getPassword().equals(password))
			{
				model = new ModelAndView("forward:/home");
				model.addObject("user", u);
			}
			else
			{
				model = new ModelAndView("index");
				model.addObject("error", "password");
			}
		}
		else
		{
			model = new ModelAndView("index");
			model.addObject("error", "user");
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
			@RequestParam("first_name") String first_name,
			@RequestParam("middle_name") String middle_name,
			@RequestParam("last_name") String last_name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("cpassword") String cpassword)
	{
		ModelAndView model = new ModelAndView("index");
		
		if (password.equals(cpassword))
		{
			PersonBO personService = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
			UserBO userService = ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class);
			
			// Create person
			Person p = new Person();
			p.setFirstName(first_name);
			p.setMiddleName(middle_name);
			p.setLastName(last_name);
			p.setEmail(email);
			personService.insert(p);
						
			// Create user
			User u = new User();
			u.setPerson(p);
			u.setPassword(password);
			u.setUser(BeanUtil.generateUserName(p));
			userService.insert(u); 
			
			model.addObject("page", "confirmation");
			model.addObject("account_name", u.getUser());
		}
		else
		{
			model.addObject("page", "register");
			model.addObject("error", "password");
		}
		
		return model;
	}
}
