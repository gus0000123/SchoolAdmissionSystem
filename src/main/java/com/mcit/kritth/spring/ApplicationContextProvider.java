package com.mcit.kritth.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import spring.StartSpring;

public class ApplicationContextProvider implements ApplicationContextAware
{
	@Autowired
	private static ApplicationContext context;
	
	public static ApplicationContext getApplicationContext()
	{
		if (context == null)
		{
			StartSpring.init();
		}
		return context;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException
	{
		context = ac;
	}
}
