package com.readlearncode.model.contraints;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {StockCodeValidator.class})
public @interface StockCode {

    String message() default "{com.readlearncode.model.constraints.StockCode.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int size() default 0;

}
