package com.bing.boot.common.annotation;

import com.bing.boot.common.handler.CallFrequencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Description:方法调用频率注解
 * Author: zhangfusheng
 * Date: 2018/2/26 上午11:14
 */
@Retention(RetentionPolicy.RUNTIME)
//@Target( {ElementType.METHOD} )
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = {CallFrequencyValidator.class})
public @interface CallFrequency {

    /**
     * 校验参数
     * @return
     */
    String key() default "";

    /**
     * 指定时间间隔内调用的次数
     * @return
     */
    int times() default 3;

    /**
     * 时间间隔
     * @return
     */
    int duration() default 10;

    /**
     * 时间间隔单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;

    /**
     * 错误提示信息
     * @return
     */
    String message() default "已达到最大调用次数，请稍后再试！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
