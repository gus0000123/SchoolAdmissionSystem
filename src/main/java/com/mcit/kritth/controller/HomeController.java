package com.mcit.kritth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.model.data.User;

@Controller
@SessionAttributes("user")
public class HomeController
{
	@RequestMapping("/home")
	public ModelAndView tabSelector(
			@ModelAttribute User user)
	{
		return null;
	}
}
