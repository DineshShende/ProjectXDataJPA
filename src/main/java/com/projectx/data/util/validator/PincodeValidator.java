package com.projectx.data.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.data.util.annotation.Pincode;

public class PincodeValidator implements ConstraintValidator<Pincode, Integer> {

	@Override
	public void initialize(Pincode constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value >100000 && value<999999)
			return true;
		
		return false;
	}

}
