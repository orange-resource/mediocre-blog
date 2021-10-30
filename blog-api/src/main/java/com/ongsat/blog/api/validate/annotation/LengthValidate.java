package com.ongsat.blog.api.validate.annotation;

import com.ongsat.blog.api.validate.logic.LengthValidateLogic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthValidateLogic.class)
public @interface LengthValidate {

    int min();

    int max();

    boolean emptyRequired() default true;

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
