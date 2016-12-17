package com.mcit.kritth.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.mcit.kritth.configuration.AppConfig;

public class ApplicationContextProvider implements ApplicationContextAware
{
	@Autowired
	private static ApplicationContext context;
	
	public static ApplicationContext getApplicationContext()
	{
		if (context == null)
		{
			AbstractApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
			ApplicationContextProvider provider = new ApplicationContextProvider();
			provider.setApplicationContext(appContext);
		}
		return context;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException
	{
		context = ac;
	}
}
