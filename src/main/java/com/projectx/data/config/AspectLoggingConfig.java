package com.projectx.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.projectx.data.util.aspect.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AspectLoggingConfig {

	@Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

}
