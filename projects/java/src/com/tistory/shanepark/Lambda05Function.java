package com.tistory.shanepark;

import java.util.function.Function;

public class Lambda05Function {

    public static void main(String[] args) {
        Function<String, Integer> f1 = s -> s.length();
        int result = f1.apply("hello");
        System.out.println(result);
    }

}
