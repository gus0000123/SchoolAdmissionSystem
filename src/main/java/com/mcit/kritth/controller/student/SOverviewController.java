package com.mcit.kritth.controller.student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
@SessionAttributes("user")
public class SOverviewController
{
	private StudentBO sservice;
	
	@RequestMapping(value = "/studentOverview", method = RequestMethod.POST)
	public ModelAndView getOverviewContext(
			@ModelAttribute User u)
	{
		if (sservice == null) sservice = ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class);
		
		Student student = sservice.getById(u.getPerson().getID());
		
		// Link
		ModelAndView model = new ModelAndView("layout/studentApp");
				
		// Attach
		model.addObject("tab", "overview");
		model.addObject("student", student);
		model.addObject("assignments", getAssignments(student));
		
		return model;
	}
	
	private List<CourseWork> getAssignments(Student student)
	{
		ArrayList<CourseWork> result = new ArrayList<>();
		Date today = new Date();
		
		for (Course c : student.getEnrolled_courses())
		{
			for (CourseWork cw : c.getCourse_works())
			{
				if (cw.getDeadline().compareTo(today) >= 0)
				{
					result.add(cw);
				}
			}
		}
		
		result.sort((o1, o2) -> o1.getDeadline().compareTo(o2.getDeadline()));
		
		return result;
	}
}
