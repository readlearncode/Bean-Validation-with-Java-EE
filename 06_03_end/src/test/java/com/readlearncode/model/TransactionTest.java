package com.readlearncode.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class TransactionTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Test
    public void givenTransactionPOJO_whenDataValid_shouldValidate() throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setQuantity(10);
        transaction.setPriceLimit(1.50);
        transaction.setType(Transaction.TYPE.BUY);
        transaction.setStock(new Stock());
        transaction.setExerciseDate(dateFormat.parse("2020/12/2"));

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenTransactionPOJO_whenDataInvalid_shouldNotValidate() {
        Transaction transaction = new Transaction();
        transaction.setQuantity(-10);
        transaction.setPriceLimit(12345678912.5555);
        transaction.setType(null);
        transaction.setStock(null);
        transaction.setExerciseDate(null);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertThat(violations.size()).isEqualTo(5);
    }

    @Test
    public void givenTransactionPOJO_whenPriceHasLongFraction_shouldNotValidate() {
        Transaction transaction = new Transaction();
        transaction.setPriceLimit(1.5555);
        Set<ConstraintViolation<Transaction>> violations = validator.validateProperty(transaction, "priceLimit");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenTransactionPOJO_whenPriceHasLongIntegral_shouldNotValidate() {
        Transaction transaction = new Transaction();
        transaction.setPriceLimit(12345678901.55);
        Set<ConstraintViolation<Transaction>> violations = validator.validateProperty(transaction, "priceLimit");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenTransactionPOJO_whenPriceIsZero_shouldNotValidate() {
        Transaction transaction = new Transaction();
        transaction.setPriceLimit(0.0);
        Set<ConstraintViolation<Transaction>> violations = validator.validateProperty(transaction, "priceLimit");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenTransactionPOJO_whenPriceIsNegative_shouldNotValidate() {
        Transaction transaction = new Transaction();
        transaction.setPriceLimit(-1.0);
        Set<ConstraintViolation<Transaction>> violations = validator.validateProperty(transaction, "priceLimit");
        assertThat(violations.size()).isEqualTo(1);
    }

    // challenge solutions
    @Test
    public void givenTransactionPOJO_whenQuantityNegative_shouldNotValidate() {
        Transaction transaction = new Transaction();
        transaction.setQuantity(-1);
        Set<ConstraintViolation<Transaction>> violations = validator.validateProperty(transaction, "quantity");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenTransactionPOJO_whenQuantityPositive_shouldValidate() {
        Transaction transaction = new Transaction();
        transaction.setQuantity(10);
        Set<ConstraintViolation<Transaction>> violations = validator.validateProperty(transaction, "quantity");
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenPriceNegative_shouldPassCustomisedValidationFailureMessage() {
        Set<ConstraintViolation<Transaction>> violations = validator.validateValue(Transaction.class, "priceLimit", -500d);
        assertThat(violations.size()).isNotZero();
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Please enter a valid price more than 0 and less than 100");
    }

    @Test
    public void givenDateInThePast_shouldPassCustomisedValidationFailureMessage() throws ParseException {
        Set<ConstraintViolation<Transaction>> violations = validator.validateValue(Transaction.class, "exerciseDate", dateFormat.parse("1900/01/01"));
        assertThat(violations.size()).isNotZero();
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The date " + dateFormat.parse("1900/01/01") + " is invalid. It must be current or future");
    }

}