package com.tistory.shanepark.stream;

import java.util.Arrays;

public class Stream01 {
    public static void main(String[] args) {

        Integer[] numbers = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        Arrays.stream(numbers).forEach(System.out::println);

    }
}
