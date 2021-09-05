package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream07Reduce {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10});

        int sum = list.stream().reduce(0, (a,b) -> a+b);

        System.out.println(sum);

    }
}
