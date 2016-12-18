package com.mcit.kritth.controller.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.PersonalBO;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.model.messenger.Personal;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
@SessionAttributes("user")
public class OverviewController {
	@RequestMapping(value = "/homeOverview", method = RequestMethod.POST)
	public ModelAndView getOverviewContext(
			@ModelAttribute User u)
	{
		// Grabbing data
		Person p = u.getPerson();
		PersonalBO service = ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class);
		
		// Initialization
		List<Personal> obj = service.getAllFromReceiverId(p.getID()); 
		List<Personal> nonImportant = new ArrayList<>();
		List<Personal> important = new ArrayList<>();
		
		// Sort
		Collections.sort(obj, new Comparator<Personal>()
		{
			@Override
			public int compare(Personal p1, Personal p2)
			{
				return p2.getCreation_time().compareTo(p1.getCreation_time());
			}
		});
		
		int limit = 3;			// Limit to show on each section, otherwise the page will be flooded
		boolean non_max = false;
		boolean imp_max = false;
		int non_counter = 0;
		int imp_counter = 0;
				
		// Sort into category
		for (Personal o : obj)
		{
			Personal pm = o;
			// Check which list to put into
			if (pm.isImportant())
			{
				if (imp_counter < limit) important.add(pm);
				else imp_max = true;
			}
			else
			{
				if (non_counter < limit) nonImportant.add(pm);
				else non_max = true;
			}
			
			if (imp_max && non_max) break;
		}
		
		// Link
		ModelAndView model = new ModelAndView("layout/homeApp");
		
		// Attach
		model.addObject("tab", "overview");
		model.addObject("important_messages", important);
		model.addObject("messages", nonImportant);
		
		return model;
	}
}
