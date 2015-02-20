package com.projectx.data.controller.quickregister;

import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(value=InterruptedException.class)
	@ResponseStatus(value=HttpStatus.ALREADY_REPORTED,reason="ABC")
	public void exception()
	{
		
	}
	
}
