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
        Client client = new Client(null, null, null, null,null, "5105105105105100");
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test
    public void givenClientPOJO_whenEmailDataInvalid_shouldValidate() {
        Set<ConstraintViolation<Client>> violations = validator.validateValue(Client.class, "email", "invalid_email");
        assertThat(violations.size()).isEqualTo(1);
    }
}