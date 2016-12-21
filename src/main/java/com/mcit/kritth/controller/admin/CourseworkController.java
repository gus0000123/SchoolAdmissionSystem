package com.mcit.kritth.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
public class CourseworkController
{
	private CourseWorkBO cwservice;
	private CourseBO cservice;
	
	private void init()
	{
		cwservice = ApplicationContextProvider.getApplicationContext().getBean(CourseWorkBO.class);
		cservice = ApplicationContextProvider.getApplicationContext().getBean(CourseBO.class);
	}
	
	@RequestMapping(value="/courseworkController", method = RequestMethod.POST)
	public ModelAndView courseworkContentSelector(
			@RequestParam(value = "mode", required=false) String mode)
	{
		if (cwservice == null) init();
		
		String url = "forward:/courseStartEdit";
		
		if (mode == null) mode = "other";
		
		switch(mode)
		{
			case "insert":
				url = "forward:/courseworkDoInsert";
				break;
			default:
				break;
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "course");
		model.addObject("mode", "edit");
		
		return model;
	}
	
	@RequestMapping(value="/courseworkDoInsert", method=RequestMethod.POST)
	public ModelAndView courseworkDoInsert(
			@RequestParam("cw_coursework_name") String coursework_name,
			@RequestParam("cw_coursework_description") String coursework_description,
			@RequestParam("cw_contribution") Integer contribution,
			@RequestParam("cw_max_mark") Integer max_mark,
			@RequestParam("cw_deadline") String deadline_date,
			@RequestParam("cw_deadline_time") String deadline_time,
			@RequestParam("course_code") String course_code)
	{
		String url = "forward:/courseStartEdit";
		
		ModelAndView model = new ModelAndView(url);
		
		CourseWork cw = new CourseWork();
		cw.setCoursework_name(coursework_name);
		cw.setCoursework_description(coursework_description);
		cw.setContribution(((double) contribution) / 100.0);
		cw.setMax_mark(max_mark);
		String deadline = deadline_date + "/" + deadline_time;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy/hh:mm");
		try {
			cw.setDeadline(dateFormat.parse(deadline));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			Course c = cservice.getById(course_code);
			cw.setCourse(c);
			cwservice.insert(cw);
			c.getCourse_works().add(cw);
			cservice.update(c);
		} catch (ObjectNotFoundException ex) {
			model.addObject("error", "Cannot find specified course");
			ex.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
}
