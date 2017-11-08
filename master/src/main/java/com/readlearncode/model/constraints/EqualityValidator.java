package com.readlearncode.model.constraints;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class EqualityValidator implements ConstraintValidator<EqualFields, Object> {

    private String firstField;

    private String secondField;

    @Override
    public void initialize(EqualFields constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        try {
            return BeanUtils.getProperty(bean, firstField).equals(BeanUtils.getProperty(bean, secondField));
        } catch (Exception ex) {
            return false;
        }
    }

}