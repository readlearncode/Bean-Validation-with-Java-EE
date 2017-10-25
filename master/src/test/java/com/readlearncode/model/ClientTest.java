package com.readlearncode.model;

import org.junit.Ignore;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
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
        Client client = new Client(1L, "John Smith", dateFormat.parse("1978/12/2"), "alex@asdwer.com", true);
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void givenClientPOJO_whenDataInvalid_shouldNotValidate() throws ParseException {
        Client client = new Client(null, null, null, null, null);
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test @Ignore
    public void givenCollection_whenDataInvalid_shouldNotValidate()  {
        List<@NotBlank String> things = null;
        Set<ConstraintViolation<List<String> >> violations = validator.validate(things);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test @Ignore
    public void givenOptional_whenDataInvalid_shouldNotValidate()  {
        Optional<@Valid @NotEmpty String> things = Optional.of("");
        Set<ConstraintViolation<Optional<String>>> violations = validator.validate(things);
        assertThat(violations.size()).isEqualTo(1);
    }
}