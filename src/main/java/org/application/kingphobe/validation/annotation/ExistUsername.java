package org.application.kingphobe.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.application.kingphobe.validation.validator.ExistUsernameValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ExistUsernameValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExistUsername {
	
	public String message() default "Username already exists";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
