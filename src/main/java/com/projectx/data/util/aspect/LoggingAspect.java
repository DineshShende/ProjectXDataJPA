package com.projectx.data.util.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.projectx.data.domain.completeregister.CustomerDetails;

@Aspect
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	org.apache.log4j.Logger abc=org.apache.log4j.Logger.getLogger(this.getClass());
	
	
	
	 @Pointcut("within(com.projectx.data.repository.completeregister..*) || within(com.projectx.data.repository.quickregister..*) "
	 		+ " || within(com.projectx.data.repository.request..*) ")
	    public void loggingPoincutRespository() {}
	
	 
	 @Pointcut("within(com.projectx.data.controller.completeregister..*) || within(com.projectx.data.controller.quickregister..*)"
		 		+ "|| within(com.projectx.data.controller.request..*) || within(com.projectx.data.controller.response..*)")
		    public void loggingPoincutController() {}
	 
	 	@Around("loggingPoincutRespository() || loggingPoincutController()")
	    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	 		
	 		if (log.isDebugEnabled()) {
	 			
	        	log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	        }
	        try {
	            Object result = joinPoint.proceed();
	            if (log.isDebugEnabled()) {
	                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                        joinPoint.getSignature().getName(), result);
	            }
	            return result;
	        } catch (IllegalArgumentException e) {
	            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
	                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

	            throw e;
	        }
	    }

	
	    @AfterThrowing(pointcut = "loggingPoincutRespository() || loggingPoincutController()", throwing = "e")
	    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
	        
	            log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                    joinPoint.getSignature().getName(), e.getCause());
	        
	    }

}
