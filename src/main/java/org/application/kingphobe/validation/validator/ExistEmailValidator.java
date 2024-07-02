package org.application.kingphobe.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.application.kingphobe.common.utils.ValidatorUtils;
import org.application.kingphobe.service.UserService;
import org.application.kingphobe.validation.annotation.ExistEmail;


public class ExistEmailValidator implements ConstraintValidator<ExistEmail, String> {

	private String message;
	private UserService userService;
	
	public ExistEmailValidator(UserService service) {
		userService=service;
	}
	
	@Override
	public void initialize(ExistEmail constraintAnnotation) {
		message=constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(userService.getUserByEmail(email).isEmpty())
			return true;

		ValidatorUtils.addError(context, message);
		return false;
	}
	
	
}
