package com.projectx.data.util.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.projectx.data.domain.quickregister.QuickRegisterEntity;

public class CustomerQuickRegisterEntityValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
			return QuickRegisterEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		QuickRegisterEntity entity=(QuickRegisterEntity)target;
		
		if(entity.getEmail()==null && entity.getMobile()==null)
			errors.rejectValue("email", "Both Can't be Null", "Both email and mobile can't be Null");
	}


}
