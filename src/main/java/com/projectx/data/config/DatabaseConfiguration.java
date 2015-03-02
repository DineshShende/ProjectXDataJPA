package com.projectx.data.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages="com.projectx.data.repository")
@EntityScan(basePackages="com.projectx.data.domain")
public class DatabaseConfiguration {


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
