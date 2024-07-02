package org.application.kingphobe.common.utils;

import jakarta.validation.ConstraintValidatorContext;

public class ValidatorUtils {
    public static void addError(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
