package com.ongsat.blog.api.validate.logic;

import com.ongsat.blog.api.validate.annotation.NumberLimitValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberLimitValidateLogic implements ConstraintValidator<NumberLimitValidate,Integer> {

    private int[] number;

    @Override
    public void initialize(NumberLimitValidate numberLimitValidate) {
        this.number = numberLimitValidate.number();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (null != integer) {
            for (int num : number) {
                if (num == integer) return true;
            }
        }

        return false;
    }
}
