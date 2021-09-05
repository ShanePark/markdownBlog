package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;

public class Stream05 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,5,5,4,3,1,7,10,1,9};
        List<Integer> list = Arrays.asList(arr);

        list.stream().distinct()
                    .limit(5)
                    .sorted()
                    .filter(n -> n<=5)
                    .forEach(System.out::println);

    }
}
