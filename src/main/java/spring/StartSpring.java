package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.mcit.kritth.configuration.AppConfig;
import com.mcit.kritth.spring.ApplicationContextProvider;

public class StartSpring
{
	private static boolean started = false;

	public static void init()
	{
		if (started == false)
		{
			AbstractApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
			ApplicationContextProvider context = new ApplicationContextProvider();
			context.setApplicationContext(appContext);
			started = true;
		}
	}
	
	private StartSpring() { }
}
