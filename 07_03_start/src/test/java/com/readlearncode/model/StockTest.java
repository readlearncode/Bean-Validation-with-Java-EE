package com.readlearncode.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class StockTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void givenStockPOJO_whenCodeValid_shouldValidate() {
        Stock stock = new Stock();
        stock.setCode("XXX");
        Set<ConstraintViolation<Stock>> violations = validator.validateProperty(stock, "code");
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenStockPOJO_whenCodeTooShort_shouldNotValidate() {
        Stock stock = new Stock();
        stock.setCode("X");
        Set<ConstraintViolation<Stock>> violations = validator.validateProperty(stock, "code");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenStockPOJO_whenCodeTooLong_shouldNotValidate() {
        Stock stock = new Stock();
        stock.setCode("XXXX");
        Set<ConstraintViolation<Stock>> violations = validator.validateProperty(stock, "code");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenStockPOJO_whenCodeNull_shouldNotValidate() {
        Stock stock = new Stock();
        stock.setCode(null);
        Set<ConstraintViolation<Stock>> violations = validator.validateProperty(stock, "code");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenStockCode_shouldPassCustomisedValidationFailureMessage() {
        Set<ConstraintViolation<Stock>> violations = validator.validateValue(Stock.class, "code", "XXXX");
        assertThat(violations.size()).isNotZero();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Stock must be 3 characters in length");
    }
}