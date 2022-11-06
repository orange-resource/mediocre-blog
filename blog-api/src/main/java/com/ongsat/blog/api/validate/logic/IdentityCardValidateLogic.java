package com.ongsat.blog.api.validate.logic;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import com.ongsat.blog.api.validate.annotation.IdentityCardValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentityCardValidateLogic implements ConstraintValidator<IdentityCardValidate,String> {

    private boolean blankRequired;

    @Override
    public void initialize(IdentityCardValidate identityCardValidate) {
        this.blankRequired = identityCardValidate.blankRequired();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (blankRequired) {
            return IdcardUtil.isValidCard(s);
        } else {
            // 不空白的时候才要验证
            if (!StrUtil.isBlank(s)) {
                return IdcardUtil.isValidCard(s);
            }
            return true;
        }
    }
}
