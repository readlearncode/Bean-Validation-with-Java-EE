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
public class PortfolioTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


    @Test
    public void givenPortfolioPOJO_whenDataInvalid_shouldNotValidate() throws ParseException {
        Portfolio portfolio = new Portfolio(null, null, null);
        Set<ConstraintViolation<Portfolio>> violations = validator.validate(portfolio);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenPortfolioPOJO_whenDataValid_shouldValidate() throws ParseException {
        Transaction transaction = new Transaction(1, Transaction.TYPE.BUY, new Stock(), 200, 2D, dateFormat.parse("1978/12/2"));
        Portfolio portfolio = new Portfolio(null, null, transaction);
        Set<ConstraintViolation<Portfolio>> violations = validator.validate(portfolio);
        assertThat(violations.size()).isEqualTo(0);
    }

}