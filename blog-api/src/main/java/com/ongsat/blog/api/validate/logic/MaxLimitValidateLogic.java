package com.ongsat.blog.api.validate.logic;

import com.ongsat.blog.api.validate.annotation.MaxLimitValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxLimitValidateLogic implements ConstraintValidator<MaxLimitValidate,Integer> {

    private int number;

    private int[] cantInput;

    @Override
    public void initialize(MaxLimitValidate maxLimitValidate) {
        this.number = maxLimitValidate.value();
        this.cantInput = maxLimitValidate.cantInput();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (null == integer) return false;

        for (int n : cantInput) {
            if (n == integer) return false;
        }

        if (integer <= number) return true;

        return false;
    }
}
