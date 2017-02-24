package com.mcit.kritth.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.model.data.User;

@Controller
@SessionAttributes("user")
public class AdminController
{
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ModelAndView tabSelector(
			@ModelAttribute User u,
			@RequestParam(value = "tab", required = false) String tab)
	{
		// User not available
		if (u == null) return new ModelAndView("redirect:/");
		
		String url = "";
		if (tab == null) tab = "person";
		
		switch(tab)
		{
			case "student":
				url = "forward:/student";
				break;
			case "course":
				url = "forward:/course";
				break;
			case "coursework":
				url = "forward:/coursework";
				break;
			case "person":
			default:
				url = "forward:/person";
				break;
		}
		
		return new ModelAndView(url);
	}
}
