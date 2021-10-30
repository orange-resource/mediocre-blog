package com.ongsat.blog.api.validate.logic;

import com.ongsat.blog.api.validate.annotation.LengthValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthValidateLogic implements ConstraintValidator<LengthValidate,String> {

    private int min;
    private int max;
    private boolean emptyRequired;

    @Override
    public void initialize(LengthValidate lengthValidate) {
        this.min = lengthValidate.min();
        this.max = lengthValidate.max();
        this.emptyRequired = lengthValidate.emptyRequired();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (emptyRequired) {
            return valid(s);
        } else {
            // 不空白的时候才要验证
            if (null != s) {
                return valid(s);
            }
            return true;
        }
    }

    private boolean valid(String v) {
        if (null != v) {
            if (v.length() >= min && v.length() <= max) {
                return true;
            }
        }
        return false;
    }
}
