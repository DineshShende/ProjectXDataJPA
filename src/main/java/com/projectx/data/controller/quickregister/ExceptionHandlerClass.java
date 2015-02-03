package com.projectx.data.controller.quickregister;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(value=Exception.class)
	@ResponseStatus(value=HttpStatus.ALREADY_REPORTED,reason="ABC")
	public void exception()
	{
		
	}
	
}
