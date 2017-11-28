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

    }

    @Test
    public void givenTransactionPOJO_whenDataInvalid_shouldNotValidate() {

    }

    @Test
    public void givenTransactionPOJO_whenPriceHasLongFraction_shouldNotValidate() {

    }

    @Test
    public void givenTransactionPOJO_whenPriceHasLongIntegral_shouldNotValidate() {

    }

    @Test
    public void givenTransactionPOJO_whenPriceIsZero_shouldNotValidate() {

    }

    @Test
    public void givenTransactionPOJO_whenPriceIsNegative_shouldNotValidate() {

    }

}