package com.ongsat.blog.api.validate.logic;

import cn.hutool.core.util.StrUtil;
import com.ongsat.blog.api.validate.annotation.StringLimitValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringLimitValidateLogic implements ConstraintValidator<StringLimitValidate,String> {

    private String[] string;

    @Override
    public void initialize(StringLimitValidate stringLimitValidate) {
        this.string = stringLimitValidate.string();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StrUtil.isEmpty(s)) return false;

        for (String str : string) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
