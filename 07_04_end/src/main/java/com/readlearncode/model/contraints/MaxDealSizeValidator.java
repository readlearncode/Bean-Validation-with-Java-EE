package com.readlearncode.model.contraints;

import com.readlearncode.model.Transaction;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class MaxDealSizeValidator implements ConstraintValidator<MaxDealSize, Transaction> {

    private Double size;

    private Transaction.TYPE type;

    @Override
    public void initialize(MaxDealSize constraintAnnotation) {
        this.size = constraintAnnotation.size();
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext context) {
        boolean isValid = type.equals(transaction.getType())
                && transaction.getQuantity() * transaction.getPriceLimit()
                < size;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{com.readlearncode.model.constraints.MaxDealSize.message}")
                    .addPropertyNode("quantity").addConstraintViolation();
        }

        return isValid;
    }
}