package com.mcit.kritth.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mcit.kritth")
public class AppConfig extends WebMvcConfigurerAdapter
{
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
	}
	
	// MVC Mapping
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	// Language interceptors
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}
	
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver()
	{
		return new CookieLocaleResolver();
	}
	
	@Bean
	public MessageSource messageSource()
	{
		final ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
		ret.setBasename("/WEB-INF/jsp/layout/transcript");
		ret.setDefaultEncoding("UTF-8");
		return ret;
	}
}
