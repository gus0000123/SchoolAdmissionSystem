package com.mcit.kritth.controller.student;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
@SessionAttributes("user")
public class StudentController
{
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public ModelAndView tabSeletor(
			@ModelAttribute User user,
			@RequestParam(value = "tab", required = false) String tab)
	{
		String url = "";
		
		if (user != null && user.getPerson() != null)
		{
			StudentBO sservice = ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class);
			Student student = null;
			
			// Get student from database
			try { student = sservice.getById(user.getPerson().getID()); }
			catch (ObjectNotFoundException ex) { student = null; }
			
			// If student exists for this person
			if (student != null)
			{
				if (tab == null) tab = "overview";
				
				switch(tab)
				{
					case "transcript":
						url = "forward:/studentTranscript";
						break;
					case "register":
						url = "forward:/studentCourseRegister";
						break;
					case "overview":
					default:
						url = "forward:/studentOverview";
						break;
				}
			}
			else
			{
				url = "forward:/homeOverview";
			}
		}
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
}
