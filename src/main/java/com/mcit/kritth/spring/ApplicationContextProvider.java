package com.mcit.kritth.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mcit.kritth.bo.BO;

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
	@SuppressWarnings("unchecked")
	public static <T> BO<T> getService(String name) { return getApplicationContext().getBean(name, BO.class); }
	
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException
	{
		context = ac;
	}
}
