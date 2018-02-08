package com.bing.boot.common.handler;

import com.bing.boot.common.annotation.BlackPhone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Description:黑名单手机号校验规则
 * Author: zhangfusheng
 * Date: 2018/2/8 上午10:40
 */
public class BlackPhoneValidator implements ConstraintValidator<BlackPhone, String> {

    private List<String> paramBlackPhoneList;

    @Value("${init.blackPhone}")
    private String initBlackPhones;

    @Override
    public void initialize(BlackPhone constraintAnnotation) {
        String list = constraintAnnotation.list();
        if (!StringUtils.isEmpty(list)) {
            paramBlackPhoneList = Arrays.asList(list.split(","));
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(value)) {
            if (!CollectionUtils.isEmpty(paramBlackPhoneList)) {
                if (paramBlackPhoneList.contains(value)) {
                    return false;
                }
            }
            if (!StringUtils.isEmpty(initBlackPhones)) {
                if (initBlackPhones.contains(value)) {
                    return false;
                }
            }
        }
        return true;
    }
}
