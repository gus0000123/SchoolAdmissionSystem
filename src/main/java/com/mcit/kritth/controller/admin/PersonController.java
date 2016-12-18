package com.mcit.kritth.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
public class PersonController
{
	@RequestMapping(value = "/personController", method = RequestMethod.POST)
	public ModelAndView personActionSelector(
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "actionPerformed", required = false) Boolean performed)
	{
		String url = "";
		if (mode == null) { mode = "view"; }
		if (performed == null) { performed = false; }
		
		switch(mode)
		{
			case "edit":
				if (!performed) url = "forward:/personStartEdit";
				else url = "forward:/personDoEdit";
				break;
			case "insert":
				if (!performed) url = "forward:/personStartInsert";
				else url = "forward:/personDoInsert";
				break;
			case "delete":
				url = "forward:/personDoDelete";
				break;
			case "view":
			default:
				url = "forward:/personView";
				break;
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "person");
		model.addObject("mode", mode);
		
		return model;
	}
	
	@RequestMapping(value = "/personView", method = RequestMethod.POST)
	public ModelAndView viewPerson()
	{
		String url = "layout/adminApp";
		
		PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		List<Person> list = service.getAll();
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("list", list);
		
		return model;
	}
	
	// TODO: Add all parameters
	@RequestMapping(value = "personStartInsert", method = RequestMethod.POST)
	public ModelAndView initInsertPerson()
	{
		String url = "layout/adminApp";
		
		return new ModelAndView(url);
	}
	
	// TODO: Add all parameters
	@RequestMapping(value = "/personStartEdit", method = RequestMethod.POST)
	public ModelAndView initEditPerson(
			@RequestParam("id") Integer id)
	{
		String url = "layout/adminApp";
		
		PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		Person p = service.getById(id);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("p_id", p.getID());
		model.addObject("p_first_name", p.getFirstName());
		model.addObject("p_middle_name", p.getMiddleName());
		model.addObject("p_last_name", p.getLastName());
		if (p.getAddress() != null)
		{
			model.addObject("p_street_address", p.getAddress().getStreetAddress());
			model.addObject("p_city", p.getAddress().getCity());
			model.addObject("p_state", p.getAddress().getState());
			model.addObject("p_country", p.getAddress().getCountry());
			model.addObject("p_postal", p.getAddress().getPostal());
		}
		model.addObject("p_tel_no", p.getTelNo());
		model.addObject("p_email", p.getEmail());
		model.addObject("p_gender", p.getGender());
		model.addObject("p_sin", p.getSin());
		
		return model;
	}
	
	@RequestMapping(value = "personDoInsert", method = RequestMethod.POST)
	public ModelAndView doInsertPerson(
			@RequestParam("p_first_name") String first_name,
			@RequestParam("p_last_name") String last_name,
			@RequestParam("p_email") String email)
	{
		String url = "forward:/personView";
		
		PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		Person p = new Person();
		p.setFirstName(first_name);
		p.setLastName(last_name);
		p.setEmail(email);
		service.insert(p);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "personDoEdit", method = RequestMethod.POST)
	public ModelAndView doEditPerson(
			@RequestParam("id") String id,
			@RequestParam("p_first_name") String first_name,
			@RequestParam("p_last_name") String last_name,
			@RequestParam("p_email") String email)
	{
		String url = "forward:/personView";
		
		PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		int iid = Integer.parseInt(id);
		Person p = service.getById(iid);
		p.setFirstName(first_name);
		p.setLastName(last_name);
		p.setEmail(email);
		service.update(p);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "personDoDelete", method = RequestMethod.POST)
	public ModelAndView doDeletePerson(
			@RequestParam("selection") List<String> selection)
	{
		String url = "forward:/personView";
		
		PersonBO service = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		for (String id : selection)
		{
			service.deleteById(Integer.parseInt(id));
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
}