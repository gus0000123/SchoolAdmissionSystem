package com.mcit.kritth.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class PersonalInfoController
{
	@RequestMapping(value = "/homeInfo", method = RequestMethod.POST)
	public ModelAndView getPersonalInfoContext()
	{
		ModelAndView model = new ModelAndView("layout/homeApp");
		model.addObject("tab", "info");
		return model;
	}
}
