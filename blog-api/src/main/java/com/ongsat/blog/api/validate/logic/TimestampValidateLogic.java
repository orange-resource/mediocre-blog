package com.ongsat.blog.api.validate.logic;

import com.ongsat.blog.api.validate.annotation.TimestampValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimestampValidateLogic implements ConstraintValidator<TimestampValidate,Long> {

    private int quantity;
    private boolean blankRequired;

    @Override
    public void initialize(TimestampValidate timestampValidate) {
        this.quantity = timestampValidate.quantity();
        this.blankRequired = timestampValidate.blankRequired();
    }

    @Override
    public boolean isValid(Long l, ConstraintValidatorContext constraintValidatorContext) {
        if (blankRequired) {
            String s = String.valueOf(l);
            return quantity == s.length();
        } else {
            if (null != l) {
                String s = String.valueOf(l);
                return quantity == s.length();
            }
            return true;
        }
    }
}
