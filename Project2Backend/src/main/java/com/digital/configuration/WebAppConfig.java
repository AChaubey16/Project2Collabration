package com.digital.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc//similar to <mvc:annotation-driven> in dispatcher-servlet.xml
@ComponentScan(basePackages="org.in") //similar to <context:component-scan> in dispatcher-servlet.xml 

public class WebAppConfig extends WebMvcConfigurerAdapter{
	public WebAppConfig()
	{
		System.out.println("WebAppConfig class is loaded and instantiated");
	}
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getsCommonsMultipartResolver() {
	 CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver();
	 return multipartResolver;
	}
}
