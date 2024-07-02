package org.application.kingphobe.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.application.kingphobe.validation.validator.ExistEmailValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ExistEmailValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExistEmail {

	public String message() default "Email already exists";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
