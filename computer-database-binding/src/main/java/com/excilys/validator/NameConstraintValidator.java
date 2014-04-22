package com.excilys.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class NameConstraintValidator implements ConstraintValidator<NameValid, String> {
    
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}
	
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

