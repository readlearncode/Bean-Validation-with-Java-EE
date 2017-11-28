package com.readlearncode.model;

import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class PortfolioTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Test
    public void givenPortfolioPOJO_whenDataInvalid_shouldNotValidate() throws ParseException {

    }

    @Test
    public void givenPortfolioPOJO_whenDataValid_shouldValidate() throws ParseException {

    }

}