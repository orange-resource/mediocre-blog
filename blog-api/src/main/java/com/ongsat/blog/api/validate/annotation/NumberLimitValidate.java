package com.ongsat.blog.api.validate.annotation;

import com.ongsat.blog.api.validate.logic.NumberLimitValidateLogic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberLimitValidateLogic.class)
public @interface NumberLimitValidate {

    int[] number();

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
