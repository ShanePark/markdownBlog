package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;

public class Stream04 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,2,5,4,3,6,7,9,1,10};
        List list = Arrays.asList(arr);

        Arrays.stream(arr).sorted().forEach(System.out::println);
        list.stream().sorted().forEach(System.out::println);

    }
}
