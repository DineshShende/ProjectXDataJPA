package com.projectx.data.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;




import com.projectx.data.util.validator.PincodeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


	@Documented
	@Constraint(validatedBy = PincodeValidator.class)
	@Target( { ElementType.METHOD, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Pincode {
	  
	      
	    String message() default "{Phone}";
	      
	    Class<?>[] groups() default {};
	      
	    Class<? extends Payload>[] payload() default {};
	       
	}
	
