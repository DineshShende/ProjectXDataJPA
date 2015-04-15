package com.projectx.data.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;

import com.projectx.data.domain.completeregister.CustomerDetails;
import com.projectx.data.util.annotation.CustomerDetailsValid;


public class CustomerDetailsValidator implements ConstraintValidator<CustomerDetailsValid, CustomerDetails>{

	@Override
	public void initialize(CustomerDetailsValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(CustomerDetails value, ConstraintValidatorContext context) {
		
		
		Boolean result=true;
		
		ConstraintViolationBuilder cvb = context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate());
		
		if(value.getFirstName()==null)
		{
			cvb.addPropertyNode(value.getFirstName()).addConstraintViolation();
			result= false;
		}
		
		
		/*
		if(value.getHomeAddressId().getPincode()<100000 || value.getHomeAddressId().getPincode()>999999)
		{
			cvb.addParameterNode(value.getHomeAddressId().getPincode()).addConstraintViolation();
			result= false;
		}
		
		if(value.getHomeAddressId().getState()==null)
		{
			cvb.addPropertyNode(value.getHomeAddressId().getState()).addConstraintViolation();
			result= false;
		}
		*/
		
		if(value.getMobile()==null && value.getEmail()==null)
			result= false;
		
		
		
		return result;
		
	}

	
}
