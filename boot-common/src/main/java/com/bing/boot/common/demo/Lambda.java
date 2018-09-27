package com.bing.boot.common.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntConsumer;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2018/1/17 下午3:29
 */
public class Lambda {

    @Test
    public void listDemo() {
        List<Integer> numbers = Arrays.asList(1,2,3,6);
        numbers.forEach(number -> System.out.println(number));

        int num = 2;
        Function<Integer, Integer> stringConverter = (from) -> from * num;
//        num++;

        List<User> users = new ArrayList<User>();
        for (int i = 1; i < 10; i++) {
            users.add(new User(i, "name" + i));
        }

        users.stream().map(User::getName).forEach(System.out::println);
        users.stream().map(User::getName).forEach(user -> System.out.println(user));
    }

    @Test
    public void mapDemo() {

    }

    @Test
    public void arrayDemo() {
        int[] arr = {1,2,3,5};
        Arrays.stream(arr).map(x -> x = x + 1).forEach(System.out::println);
        Arrays.stream(arr).forEach(System.out::println);

        IntConsumer outprintln = System.out::println;
        IntConsumer errprintln = System.err::println;                                                         
        Arrays.stream(arr).forEach(outprintln.andThen(errprintln));

    }

    class User {
        private int age;

        private String name;


        public User() {
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
