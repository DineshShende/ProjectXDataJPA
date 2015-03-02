package com.projectx.data.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.data.domain.quickregister.QuickRegisterEntity;
import com.projectx.data.util.annotation.QuickRegisterEntityValid;






public class QuickRegisterEntityValidator implements ConstraintValidator<QuickRegisterEntityValid, QuickRegisterEntity>{

	@Override
	public void initialize(QuickRegisterEntityValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(QuickRegisterEntity value, ConstraintValidatorContext context) {
		
		if(value.getMobile()==null && value.getEmail()==null)
			return false;
		else
			return true;
	}

	
}