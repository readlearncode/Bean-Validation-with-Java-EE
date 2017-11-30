package com.readlearncode.model.contraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotNull
@Digits(integer = 10, fraction = 2)
@DecimalMin(value = "0", inclusive = false)
public @interface PriceLimit {
    String message() default "{com.readlearncode.model.constraint.PriceLimit.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}