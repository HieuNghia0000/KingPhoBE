package org.application.kingphobe.validation.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.application.kingphobe.common.utils.ValidatorUtils;
import org.application.kingphobe.service.UserService;
import org.application.kingphobe.validation.annotation.ExistUsername;


public class ExistUsernameValidator implements ConstraintValidator<ExistUsername, String> {

    private String message;
    private UserService userService;

    public ExistUsernameValidator(UserService service) {
        userService = service;
    }

    @Override
    public void initialize(ExistUsername constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (userService.getUserByUsername(username).isEmpty())
            return true;

        ValidatorUtils.addError(context, message);
        return false;
    }

}
