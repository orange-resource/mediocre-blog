package com.ongsat.blog.api.validate.logic;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.ongsat.blog.api.validate.annotation.PhoneNumberValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidateLogic implements ConstraintValidator<PhoneNumberValidate,String> {

    private boolean blankRequired;

    @Override
    public void initialize(PhoneNumberValidate phoneNumberValidate) {
        this.blankRequired = phoneNumberValidate.blankRequired();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (blankRequired) {
            return Validator.isMobile(s);
        } else {
            if (!StrUtil.isBlank(s)) {
                return Validator.isMobile(s);
            }
            return true;
        }
    }
}
