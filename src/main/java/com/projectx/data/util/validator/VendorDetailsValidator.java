package com.projectx.data.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.data.domain.completeregister.VendorDetails;
import com.projectx.data.util.annotation.VendorDetailsValid;



public class VendorDetailsValidator implements ConstraintValidator<VendorDetailsValid, VendorDetails>{

	@Override
	public void initialize(VendorDetailsValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(VendorDetails value, ConstraintValidatorContext context) {
		
		if((value.getMobile()==null && value.getEmail()==null)||((value.getMobile()!=null && value.getIsMobileVerified()==null)||
				(value.getEmail()!=null && value.getIsEmailVerified()==null)))
			return false;
		else
			return true;
	}

	
}
