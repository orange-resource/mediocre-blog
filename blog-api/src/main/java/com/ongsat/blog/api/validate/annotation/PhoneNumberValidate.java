package com.ongsat.blog.api.validate.annotation;

import com.ongsat.blog.api.validate.logic.PhoneNumberValidateLogic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidateLogic.class)
public @interface PhoneNumberValidate {

    boolean blankRequired() default true;

    String message() default "手机号填写不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
