package com.bing.boot.common.annotation;

import com.bing.boot.common.handler.BlackPhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * http://blog.csdn.net/wangpeng047/article/details/41722133
 * Description:黑名单手机号注解
 * Author: zhangfusheng
 * Date: 2018/2/8 上午10:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = {BlackPhoneValidator.class})
public @interface BlackPhone {

    /**
     * black phone list. split by ','
     * demo:"13000000001,1300000002"
     * @return
     */
    String list() default "";

    String message() default "手机号已被列入黑名单";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
