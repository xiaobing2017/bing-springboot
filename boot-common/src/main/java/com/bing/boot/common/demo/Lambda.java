package com.bing.boot.common.demo;

import org.junit.Test;

import java.util.Arrays;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2018/1/17 下午3:29
 */
public class Lambda {

    @Test
    public void mapDemo() {

    }

    @Test
    public void arrayDemo() {
        int[] arr = {1,2,3,5};
        Arrays.stream(arr).map(x -> x = x + 1).forEach(System.out::println);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
