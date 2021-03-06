package com.mcit.kritth.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
			BindingResult result,
			@RequestParam(value = "tab", required = false) String tab)
	{		
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
			case "coursemark":
				url = "forward:/coursemark";
				break;
			case "person":
			default:
				url = "forward:/person";
				break;
		}
		
		System.out.println("Go to " + url);
		
		return new ModelAndView(url);
	}
}
