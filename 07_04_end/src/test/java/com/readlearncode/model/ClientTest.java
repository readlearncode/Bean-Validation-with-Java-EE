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
public class ClientTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Test
    public void givenClientPOJO_whenDataValid_shouldValidate() throws ParseException {
        Client client = new Client(1, "John Smith", dateFormat.parse("1978/12/2"), "alex@mydomin.something", true, "5105105105105100");
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenClientPOJO_whenDataInvalid_shouldNotValidate() throws ParseException {
        Client client = new Client(null, null, null, null, null, "5105105105105100");
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test
    public void givenClientPOJO_whenEmailDataInvalid_shouldValidate() {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "email", "invalid_email");
        assertThat(violations.size()).isEqualTo(1);
    }

    // Challenge solutions
    @Test
    public void givenClientPOJO_whenEmailDataValid_shouldValidate() {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "email", "email@example.com");
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenClientPOJO_whenDateValid_shouldValidate() throws ParseException {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "dob", dateFormat.parse("1978/12/2"));
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenClientPOJO_whenDateInvalid_shouldValidate() throws ParseException {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "dob", dateFormat.parse("2035/12/2"));
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenClientPOJO_whenAssertTrueIsTrue_shouldValidate() throws ParseException {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "acceptTOS", true);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenClientPOJO_whenAssertTrueIsFalse_shouldValidate() throws ParseException {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "acceptTOS", false);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenClientPOJO_whenCreditCardNumberInvalid_shouldValidate() throws ParseException {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "creditCardNumber", "1234123412341234");
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenClientPOJO_whenCreditCardNumberIsValid_shouldValidate() throws ParseException {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "creditCardNumber", "6011111111111117");
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenClientPOJO_whenCreditCardNumberValidWithDashes_shouldValidate()  {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "creditCardNumber", "5105-1051/0510 5100");
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenClientPOJO_whenCreditCardNumberInvalid_shouldNotValidate()  {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "creditCardNumber", null);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void givenInvalidEmail_shouldPassCustomisedValidationFailureMessage() {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "email", "not-valid-email");
        assertThat(violations.size()).isNotZero();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("You have entered an invalid email");
    }

    @Test
    public void givenDateOfBirth_shouldPassCustomisedValidationFailureMessage() throws ParseException {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "dob", dateFormat.parse("2020/01/01"));
        assertThat(violations.size()).isNotZero();
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The date " + dateFormat.parse("2020/01/01") + " is in the future. Please enter your date of birth!");
    }

}