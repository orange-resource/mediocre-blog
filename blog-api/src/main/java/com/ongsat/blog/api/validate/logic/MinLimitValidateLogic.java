package com.ongsat.blog.api.validate.logic;

import com.ongsat.blog.api.validate.annotation.MinLimitValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinLimitValidateLogic implements ConstraintValidator<MinLimitValidate,Integer> {

    private int number;

    private int[] cantInput;

    @Override
    public void initialize(MinLimitValidate minLimitValidate) {
        this.number = minLimitValidate.value();
        this.cantInput = minLimitValidate.cantInput();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (null == integer) return false;

        for (int n : cantInput) {
            if (n == integer) return false;
        }

        if (integer >= number) return true;

        return false;
    }
}
