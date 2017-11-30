package com.readlearncode.rest;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public javax.ws.rs.core.Response toResponse(ConstraintViolationException exception) {

        final String message = exception.getConstraintViolations().stream()
                .map(cv -> extractPropertyName(cv.getPropertyPath().toString())
                        + " : " + cv.getMessage())
                .collect(Collectors.joining(", "));


        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST)
                .header("X-Validation-Failure", message)
                .build();
    }

    private String extractPropertyName(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}