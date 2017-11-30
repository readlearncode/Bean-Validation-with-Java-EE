package com.readlearncode.model.contraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class PriceValidator implements ConstraintValidator<Price, Double> {

    private int max;

    private int min;

    @Override
    public void initialize(Price constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {

        if (value == null) return false;

        if (value <= min || value >= max) {
            return false;
        }

        String[] numParts = value.toString().split("\\.");
        return !(numParts.length > 1 && numParts[1].length() > 2);
    }
}