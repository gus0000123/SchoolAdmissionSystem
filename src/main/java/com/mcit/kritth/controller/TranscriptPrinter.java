package com.mcit.kritth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.Student;

@Controller
public class TranscriptPrinter
{
	@Autowired
	private StudentBO sservice;
	
	@RequestMapping(value = "/student/print/{id}", method = RequestMethod.GET)
	public ModelAndView transcriptFormatter(
			@PathVariable("id") int student_id,
			Locale locale)
	{
		// Fetcher
		Student s;
		try { s = sservice.getById(student_id); }
		catch (ObjectNotFoundException ex) { return new ModelAndView("redirect:/"); }
		
		// Construct new coursemark to display overall
		Map<Course, CourseMark> map = new HashMap<>();
		for (CourseMark cm : s.getMarks())
		{
			CourseMark target;
			if (!map.containsKey(cm.getCoursework().getCourse()))
			{
				target = new CourseMark();
				target.setStudent(s);
				target.setCoursework(cm.getCoursework());
				target.setMark(0);
				map.put(cm.getCoursework().getCourse(), target);
			}
			else
			{
				target = map.get(cm.getCoursework().getCourse());
			}
			
			double value = ((double) cm.getMark() / (double) cm.getCoursework().getMax_mark()) * cm.getCoursework().getContribution() * 100;
			
			target.setMark(target.getMark() + (int) value);
			map.put(cm.getCoursework().getCourse(), target);
		}
		
		// Create list to pass to view
		List<CourseMark> list = new ArrayList<>();
		for (Course c : map.keySet()) { list.add(map.get(c)); }
		
		ModelAndView model = new ModelAndView("layout/transcriptPrinter");
		model.addObject("student", s);
		model.addObject("coursemarks", list);
		return model;
	}
}
