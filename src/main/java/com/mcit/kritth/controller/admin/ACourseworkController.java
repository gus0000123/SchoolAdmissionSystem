package com.mcit.kritth.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.model.data.CourseWork;

@Controller
public class ACourseworkController
{
	@Autowired
	private CourseWorkBO cwservice;
	@Autowired
	private CourseBO cservice;
	
	@RequestMapping(value="/coursework", method = RequestMethod.POST)
	public ModelAndView courseworkContentSelector(
			@RequestParam(value = "course_code") String code,
			@RequestParam(value = "mode", required=false) String mode,
			@RequestParam(value = "actionPerformed", required=false) boolean performed)
	{		
		String url = "forward:/courseStartEdit";
		
		if (mode == null) mode = "other";
		
		switch(mode)
		{
			case "insert":
				if (!performed) url = "forward:/coursework/insert";
				else url = "forward:/coursework/insert/perform";
				break;
			case "edit":
				if (!performed) url = "forward:/coursework/edit";
				else url = "forward:/coursework/edit/perform";
				break;
			default:
				url = "forward:/course/edit";
				break;
		}
		
		System.out.println("Go to " + url);
		
		ModelAndView model = new ModelAndView(url);
		if (url.equals("forward:/course/edit")) model.addObject("course_code", code);
		return model;
	}
	
	@RequestMapping(value="/coursework/insert", method=RequestMethod.POST)
	public ModelAndView courseworkStartInsert(
			@RequestParam("course_code") String course_id)
	{
		String url = "layout/adminApp";
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("coursework", new CourseWork());
		model.addObject("tab", "coursework");
		model.addObject("mode", "insert");
		model.addObject("course", cservice.getById(course_id));
		
		return model;
	}
	
	@RequestMapping(value="/coursework/insert/perform", method=RequestMethod.POST)
	public ModelAndView courseworkDoInsert(
			@RequestParam("course_code") String course_id,
			@ModelAttribute("coursework") CourseWork coursework)
	{
		String url = "forward:/course/edit";
		
		coursework.setCourse(cservice.getById(course_id));
		cwservice.insert(coursework);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("course_code", course_id);
		model.addObject("tab", "course");
		model.addObject("mode", "edit");
		
		return model;
	}
	
	@RequestMapping(value="/coursework/edit", method=RequestMethod.POST)
	public ModelAndView courseworkStartEdit(
			@RequestParam("coursework_id") int cw_id)
	{
		String url = "layout/adminApp";
		
		CourseWork cw = cwservice.getById(cw_id);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("coursework", cw);
		model.addObject("tab", "coursework");
		model.addObject("mode", "edit");
		model.addObject("course", cw.getCourse());
		
		return model;
	}
	
	@RequestMapping(value="/coursework/edit/perform", method=RequestMethod.POST)
	public ModelAndView courseworkDoEdit(
			@RequestParam("course_code") String course_id,
			@ModelAttribute("coursework") CourseWork coursework)
	{
		String url = "forward:/course/edit";
		
		coursework.setCourse(cservice.getById(course_id));
		cwservice.update(coursework);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("course_code", course_id);
		model.addObject("tab", "course");
		model.addObject("mode", "edit");
		
		return model;
	}
}
