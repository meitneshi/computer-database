package com.excilys.validator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.excilys.validator.NameConstraintValidator;

@Documented
@Constraint(validatedBy = NameConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValid {
	String message() default "NameValid.computerdto.name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

