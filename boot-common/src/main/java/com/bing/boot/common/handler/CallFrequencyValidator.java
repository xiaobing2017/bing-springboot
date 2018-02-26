package com.bing.boot.common.handler;

import com.bing.boot.common.annotation.CallFrequency;
import com.google.common.collect.Maps;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:方法调用频率校验规则
 * Author: zhangfusheng
 * Date: 2018/2/26 上午11:34
 */
public class CallFrequencyValidator implements ConstraintValidator<CallFrequency, Object> {

    private static Map<String, Integer> cacheMap = Maps.newHashMap();

    private static long time = 0L;

    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 初始化方法只执行一次
     * @param constraintAnnotation
     */
    @Override
    public void initialize(CallFrequency constraintAnnotation) {
        System.out.println("Call Frequency Validator initialize...");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            CallFrequency callFrequency = (CallFrequency) ((ConstraintValidatorContextImpl) context).getConstraintDescriptor().getAnnotation();
            String key = callFrequency.key().replace("{value}", (String) value);
            long milliSeconds = TimeUnit.MILLISECONDS.convert(callFrequency.duration(), callFrequency.timeUnit());

            // 改成redis TODO
            lock.lock();
            try {
                if (!cacheMap.containsKey(key)) {
                    time = Calendar.getInstance().getTimeInMillis();
                    cacheMap.put(key, 1);
                } else {
                    cacheMap.put(key, cacheMap.get(key) + 1);
                }
                // 在时间段范围内
                if (Calendar.getInstance().getTimeInMillis() - time <= milliSeconds) {
                    // 超过访问次数
                    if (cacheMap.get(key) > callFrequency.times()) {
                        return false;
                    }
                } else {
                    time = Calendar.getInstance().getTimeInMillis();
                    cacheMap.put(key, 1);
                }
            } finally {
                lock.unlock();
            }
        }
        return true;
    }
}
