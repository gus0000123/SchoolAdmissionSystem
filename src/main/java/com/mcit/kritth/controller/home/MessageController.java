package com.mcit.kritth.controller.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.PersonalBO;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.model.messenger.Personal;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
@SessionAttributes("user")
public class MessageController
{
	@RequestMapping(value = "/homeMessage", method = RequestMethod.POST)
	public ModelAndView getMessageContext(
			@ModelAttribute User u,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "sub_tab", required = false) String sub,
			@RequestParam(value = "select_message", required = false) Integer message_id)
	{
		// Initialize
		String url = "layout/homeApp";
		ModelAndView model = null;
		Person p = u.getPerson();
		List<Personal> list = null;
		Personal message = null;
		PersonalBO service = ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class);
		
		if (action == null || action.equals("")) { action = "other"; }
		
		// Set sub tab
		if (action.equals("other"))
		{			
			if (sub == null) { sub = "inbox"; }
			switch(sub)
			{
				case "trash":
					list = service.getTrashFromReceiverId(p.getID());
					break;
				case "sent":
					list = service.getAllFromSenderId(p.getID());
					break;
				case "inbox":
				default:
					list = service.getAllFromReceiverId(p.getID());
					break;
			}		
			
			// Set messages
			if (list != null)
			{
				// Sort
				Collections.sort(list, new Comparator<Personal>()
				{
					@Override
					public int compare(Personal p1, Personal p2)
					{
						return p2.getCreation_time().compareTo(p1.getCreation_time());
					}
				});
			}
			else { list = new ArrayList<>(); }
			
			// Attach
			model = new ModelAndView(url);
			model.addObject("all_messages", list);
			model.addObject("sub_tab", sub);
		}
		else if (action.equals("view") && message_id != null)
		{
			message = service.getById(message_id);
			
			// Attach
			model = new ModelAndView(url);
			model.addObject("mode", "view");
			model.addObject("message", message);
			model.addObject("action", "view");
		}
		else if (action.equals("compose"))
		{
			model = new ModelAndView(url);
			model.addObject("mode", "compose");
			model.addObject("action", "compose");
		}
		
		if (model == null) model = new ModelAndView("redirect:/home");
		else model.addObject("tab", "message");
		
		return model;
	}
	
	@RequestMapping(value = "/moveMessages", method = RequestMethod.POST)
	public ModelAndView moveMessages(
			@RequestParam(value = "selection", required = false) String[] selection,
			@RequestParam(value = "sub_tab", required = false) String sub_tab)
	{
		if (selection != null && sub_tab != null)
		{
			PersonalBO service = ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class);
			
			switch(sub_tab)
			{
				case "trash":
					trashAction(selection, service);
					break;
				case "inbox":
					inboxAction(selection, service);
				default:
					break;
			}
		}
		
		return new ModelAndView("forward:/home");
	}
	
	private void inboxAction(String[] selection, PersonalBO service)
	{
		for (int i = 0; i < selection.length; i++)
		{
			int id = Integer.parseInt(selection[i]);
			Personal pm = service.getById(id);
			pm.setTrash(true);
			service.update(pm);
		}
	}
	
	private void trashAction(String[] selection, PersonalBO service)
	{
		for (int i = 0; i < selection.length; i++)
		{
			int id = Integer.parseInt(selection[i]);
			Personal pm = service.getById(id);
			pm.setTrash(false);
			service.update(pm);
		}
	}
}