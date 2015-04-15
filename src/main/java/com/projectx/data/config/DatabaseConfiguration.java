package com.projectx.data.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableJpaRepositories(basePackages="com.projectx.data.repository")
@EntityScan(basePackages="com.projectx.data.domain")
public class DatabaseConfiguration {


	/*
	@Bean
	public DriverManagerDataSource dataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setPassword("dev");
		dataSource.setUrl("jdbc:mysql://localhost/spring");
		dataSource.setUsername("root");
		
		return dataSource;
	}
	*/
	
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
