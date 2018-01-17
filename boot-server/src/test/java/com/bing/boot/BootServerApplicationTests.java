package com.bing.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootServerApplicationTests {

	@Test
	public void contextLoads() {
		/**
		 * 所有整数在计算机中都是以补码方式表示
		 * 整数的反码等于原码除符号位外的其他所有位取反
		 * 20原码:00000000 00000000 00000000 00010100
		 * 20反码:01111111 11111111 11111111 11101011
		 * 20补码=20原码（正整数的补码等于原码）
		 * -20原码:100000000 00000000 00000000 00010100
		 * -20反码:111111111 11111111 11111111 11101011
		 * -20补码:111111111 11111111 11111111 11101100（负整数的补码等于反码+1）
		 */
		/**
		 * 正整数右移，即补码右移，左边空白位补0
		 * 补码结果为：00000000 00000000 00000000 00000101
		 * 原码结果为：00000000 00000000 00000000 00000101
		 * 即：5
		 */
		System.out.println(20 >> 2);
		/**
		 * 负整数右移，即补码右移，左边空白位补1
		 * 补码结果为：11111111 11111111 11111111 11111011
		 * 原码结果为：10000000 00000000 00000000 00000101
		 * 即：-5
		 */
		System.out.println(-20 >> 2);//有右移
		/**
		 * 无符号右移，即补码右移，左边空白位补0
		 * 补码结果为：00111111 11111111 11111111 11111011
		 * 原码结果为：00111111 11111111 11111111 11111011
		 * 即：2^30-5=1,073,741,819
		 */
		System.out.println(-20 >>> 2);//无符号右移
	}

}
