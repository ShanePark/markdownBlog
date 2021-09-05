package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stream07Reduce2 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10});

        Optional<Integer> max = list.stream().reduce((a, b) -> a>b?a:b);
        System.out.println(max.orElse(0));

    }
}
