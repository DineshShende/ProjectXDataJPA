package com.projectx.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:/application.properties")
public class Constants {
	

	 private Constants() {
	    
	 }
	 
	 	public static final String SPRING_PROFILE_ACTIVE_TEST ="Dev";
	 	
	 	@Value("${spring.profiles.active}")
	    public String SPRING_PROFILE_ACTIVE;
	    public static final String SPRING_PROFILE_DEVELOPMENT ="Dev";
	    public static final String SPRING_PROFILE_PRODUCTION = "Prod";
	    public static final String SPRING_PROFILE_TEST = "Test";


}
