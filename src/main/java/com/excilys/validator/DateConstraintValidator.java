package com.excilys.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<DateValid, String> {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
	@Override
	public void initialize(DateValid dateValid) {
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext cxt) {
		String dateRegex = "^|[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
		if ("".equals(date)) {
			return true;
		}
		if (date.matches(dateRegex)) {
			formatter.setLenient(false);
			try {
				formatter.parse(date);
			} catch (ParseException e) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
