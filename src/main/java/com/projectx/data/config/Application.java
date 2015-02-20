package com.projectx.data.config;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.projectx.data.util.aspect.LoggingAspect;
import com.projectx.data.util.validator.CustomerQuickRegisterEntityValidator;


@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages="com.projectx.data.repository")
@EntityScan(basePackages="com.projectx.data.domain")
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
	
	@Bean
	public CustomerQuickRegisterEntityValidator customerQuickRegisterValidator()
	{
		return new CustomerQuickRegisterEntityValidator();
	}
	
	@Bean
    
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
	
	/*
	@Bean
	@Profile("Test")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:/schema.sql")
            //.addScript("classpath:/data.sql")
            .build();

	}
	*/
}