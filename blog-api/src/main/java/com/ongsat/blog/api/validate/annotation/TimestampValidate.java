package com.ongsat.blog.api.validate.annotation;

import com.ongsat.blog.api.validate.logic.TimestampValidateLogic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimestampValidateLogic.class)
public @interface TimestampValidate {

    boolean blankRequired() default true;

    int quantity();

    String message() default "时间戳不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
