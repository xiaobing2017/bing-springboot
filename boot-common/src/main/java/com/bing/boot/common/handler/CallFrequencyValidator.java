package com.bing.boot.common.handler;

import com.bing.boot.common.annotation.CallFrequency;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;
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

    private static final String SELF = "\\{self\\}";

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
        CallFrequency callFrequency = (CallFrequency) ((ConstraintValidatorContextImpl) context).getConstraintDescriptor().getAnnotation();
        String key = convertKey(callFrequency.key(), value);
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
        return true;
    }

    private String convertKey(String key, Object value) {
        String newKey = key.replaceAll(SELF, value == null ? "" : value.toString());
        List<String> placeHolderList = Lists.newArrayList();

        int start = 0, end = 0;
        String temp = "";
        while (true) {
            start = newKey.indexOf("{", start);
            if (start == -1) break;
            end = newKey.indexOf("}", start);
            if (end == -1) break;
            if (end < start) {
                end = start;
                continue;
            }
            String placeHolder = newKey.substring(start, end + 1);
            String placeString = newKey.substring(start + 1, end);
            String[] params = placeString.indexOf(".") != -1 ? placeString.split(".") : new String[]{placeString};

            Object target = null;
            for (String param : params) {
                String methodName = "get" + param.substring(0, 1).toUpperCase() + param.substring(1);
                try {
                    Method m = value.getClass().getMethod(methodName);
                    target = m.invoke(value);
                } catch (Exception e) {
                    target = null;
                    break;
//                    e.printStackTrace();
                }
            }

            if (target != null) {
//                newKey = newKey.replaceAll(placeHolder, target.toString());
                newKey = newKey.substring(0, start) + target.toString() + newKey.substring(end + 1);
                continue;
            }

            if (end == newKey.length()) {
                break;
            }

            start = ++end;
        }

        return newKey;
    }
}
