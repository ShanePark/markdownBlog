package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream08Collect {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,2,5,4,3,1,7,10,1,9};
        List<Integer> list = Arrays.asList(arr);

        List<Integer> newList = list.stream().distinct()
                .limit(5)
                .sorted()
                .filter(n -> n<=5)
                .collect(Collectors.toList());

        System.out.println(newList);

    }
}
