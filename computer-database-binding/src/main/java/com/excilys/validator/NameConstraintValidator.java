package com.excilys.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameConstraintValidator implements ConstraintValidator<NameValid, String> {
    
	@Override
	public void initialize(NameValid nameValid) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext cxt) {
		
		String nameRegex = "^[a-zA-Z0-9]*$";
		
		if (name.isEmpty() || name == null) {
			return false;
		}
		if (name.matches(nameRegex)) {
				return true;
		} else {
			return false;
		}
	}
}

