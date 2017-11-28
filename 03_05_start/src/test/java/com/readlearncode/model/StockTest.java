package com.readlearncode.model;

import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

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

    }

    @Test
    public void givenStockPOJO_whenCodeNull_shouldNotValidate()   {

    }

    @Test
    public void givenStockPOJO_whenCodeTooShort_shouldNotValidate()   {

    }

    @Test
    public void givenStockPOJO_whenCodeTooLong_shouldNotValidate()   {

    }
}