package com.readlearncode.model.contraints;

import com.readlearncode.model.Transaction;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MaxDealSizeValidator.class})
public @interface MaxDealSize {

    String message() default "{com.readlearncode.model.constraints.MaxDealSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double size();

    Transaction.TYPE type();

}