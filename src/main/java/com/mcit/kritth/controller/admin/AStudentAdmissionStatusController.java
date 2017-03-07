package com.mcit.kritth.controller.admin;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.StudentAdmissionStatus;

@Controller
public class AStudentAdmissionStatusController
{
	@Autowired
	private StudentAdmissionStatusBO saservice;
	
	@Autowired
	private StudentBO sservice;
	
	@RequestMapping(value = "/studentAdmission/add/{status}", method = RequestMethod.GET)
	public ModelAndView studentAdmissionDoInsert(@PathVariable("status") String status)
	{
		String url = "forward:/student/view";
		
		StudentAdmissionStatus sa = new StudentAdmissionStatus();
		sa.setStatus(status);
		sa.setDescription("empty");
		
		saservice.insert(sa);
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value = "/studentAdmission/remove/{status}", method = RequestMethod.GET)
	public ModelAndView studentAdmissionDoRemove(@PathVariable("status") String status)
	{
		String url = "forward:/student/view";
		
		StudentAdmissionStatus sa, toRemove;
		try { sa = saservice.getById("Pending"); }
		catch (ObjectNotFoundException ex)
		{
			sa = new StudentAdmissionStatus();
			sa.setStatus("Pending");
			sa.setDescription("empty");
			saservice.insert(sa);
		}
		
		try
		{
			toRemove = saservice.getById(status);
			// Pending is default
			if (!status.equalsIgnoreCase("Pending"))
			{
				// Remove reference
				for (Student student : sservice.getAll())
				{
					if (student.getAdmission_status().getStatus().equalsIgnoreCase(status))
					{
						student.setAdmission_status(sa);
						sservice.update(student);
					}
				}
				
				try { saservice.delete(toRemove); }
				catch (Exception e) { e.printStackTrace(); }
			}
		}
		catch (ObjectNotFoundException ex) { }
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
}