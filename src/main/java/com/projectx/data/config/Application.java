package com.projectx.data.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import static com.projectx.data.config.Constants.*;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.projectx")

public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {

		SpringApplication.run(applicationClass, args);

		
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	private static Class<Application> applicationClass = Application.class;
	
	
	
	}