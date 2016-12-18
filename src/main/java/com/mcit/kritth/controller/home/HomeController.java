package com.mcit.kritth.controller.home;

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
public class HomeController
{
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView tabSelector(
			@ModelAttribute User user,
			@RequestParam(value = "tab", required = false) String tab)
	{
		if (user != null && user.getPerson() != null)
		{			
			String url = "";
			
			if (tab == null) { tab = "overview"; }
			
			switch (tab)
			{
				case "overview":
					url = "forward:/homeOverview";
					break;
				case "info":
					url = "forward:/homeInfo";
					break;
				case "message":
					url = "forward:/homeMessage";
					break;
				case "application":
					url = "forward:/homeApplication";
					break;
				default:
					url = "redirect:/";
					break;
			}
			
			ModelAndView model = new ModelAndView(url);
			
			return model;
		}
		else
		{
			// Session expired
			return new ModelAndView("redirect:/");
		}
	}
}
