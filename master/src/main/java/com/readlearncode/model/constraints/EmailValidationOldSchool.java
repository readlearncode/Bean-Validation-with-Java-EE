package com.readlearncode.model.constraints;

import java.util.regex.Pattern;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class EmailValidationOldSchool {

    public boolean validateEmail(final String email) throws InvalidEmailAddress {

        if (Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",email)) {
            throw new InvalidEmailAddress();
        }
            return true;
    }

    private class InvalidEmailAddress extends Throwable {}
}