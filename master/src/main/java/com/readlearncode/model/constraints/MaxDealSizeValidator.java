package com.readlearncode.model.constraints;

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
        return type.equals(transaction.getType())
                && transaction.getQuantity() * transaction.getPriceLimit()
                < size;
    }
}