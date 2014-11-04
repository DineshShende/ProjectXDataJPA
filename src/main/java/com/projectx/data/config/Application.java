package com.projectx.data.config;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


@EnableAutoConfiguration
 @EnableJpaRepositories(basePackages="com.projectx.repository")
@EntityScan(basePackages="com.projectx.domain")
@ComponentScan(basePackages = "com.projectx")

public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

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