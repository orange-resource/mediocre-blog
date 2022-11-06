package com.ongsat.blog.api.validate.annotation;

import com.ongsat.blog.api.validate.logic.MaxLimitValidateLogic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxLimitValidateLogic.class)
public @interface MaxLimitValidate {

    int value();

    int[] cantInput();

    String message() default "数字不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
