package com.bing.boot.common.demo;

import org.junit.Test;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2018/1/29 上午10:04
 */
public class Loop {

    /**
     * while(true) 和 for(;;)编译后的效果是一样的
     */

    @Test
    public void whileDemo() {
        int i = 0;
        while (true) {
//            if (i++ == 10) break;
            System.out.println(1);
        }
    }

    public void forDemo() {
        int i = 0;
        for (;;) {
//            if (i++ == 10) break;
            System.out.println(1);
        }
    }
}
