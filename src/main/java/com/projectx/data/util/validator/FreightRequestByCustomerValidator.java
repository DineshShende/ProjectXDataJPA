package com.projectx.data.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.util.annotation.FreightRequestByCustomerValid;


public class FreightRequestByCustomerValidator implements ConstraintValidator<FreightRequestByCustomerValid, FreightRequestByCustomer>{

	@Override
	public void initialize(FreightRequestByCustomerValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(FreightRequestByCustomer value, ConstraintValidatorContext context) {
		
		if((value.getIsFullTruckLoad() && value.getCapacity()==null)||(value.getIsLessThanTruckLoad() &&
				( value.getGrossWeight()==null || value.getHeight()==null || value.getWidth()==null || value.getLength()==null)))
			return false;
		else
			return true;
	}

	
}

