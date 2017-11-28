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
    public void givenStockPOJO_whenCodeValid_shouldValidate()   {
        Stock stock = new Stock();
        stock.setCode("XXX");
        Set<ConstraintViolation<Stock>> violations = validator.validateProperty(stock, "code");
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenStockPOJO_whenCodeNull_shouldNotValidate()   {
        Stock stock = new Stock();
        stock.setCode(null);
        Set<ConstraintViolation<Stock>> violations = validator.validateProperty(stock, "code");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenStockPOJO_whenCodeTooShort_shouldNotValidate()   {

    }

    @Test
    public void givenStockPOJO_whenCodeTooLong_shouldNotValidate()   {

    }

}