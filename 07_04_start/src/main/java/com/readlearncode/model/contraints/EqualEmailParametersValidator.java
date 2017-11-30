package com.readlearncode.model.contraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class EqualEmailParametersValidator implements ConstraintValidator<EqualEmailParameters, Object[]> {

    @Override
    public void initialize(EqualEmailParameters constraintAnnotation) {}

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {

        if (!(value[3] instanceof String) || !(value[4] instanceof String)) {
            throw new IllegalArgumentException("Illegal method signature. Two String parameters expected.");
        }

        return value[3].equals(value[4]);
    }
}