package com.projectx.data.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import com.projectx.data.domain.completeregister.Address;
import com.projectx.data.util.annotation.AddressValid;

public class AddressValidator implements ConstraintValidator<AddressValid, Address> {

	@Override
	public void initialize(AddressValid arg0) {

		
	}

	@Override
	public boolean isValid(Address value, ConstraintValidatorContext context) {

		ConstraintViolationBuilder cvb = context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate());
		
		if(value.getAddressLine()!=null && value.getCity()!=null && value.getCustomerType()!=null && value.getDistrict()!=null 
				&& value.getState()!=null && value.getUpdatedBy()!=null && value.getUpdateTime()!=null &&
				value.getInsertTime()!=null && (value.getPincode()>=100000 || value.getPincode()<=999999))
		
		return true;
		else
		{
			cvb.addPropertyNode(value.getState()).addConstraintViolation();
			return false;
		}
	}

}
