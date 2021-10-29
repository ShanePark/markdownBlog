package com.tistory.shanepark.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stream02 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, arr);

        list.stream().forEach(System.out::println);

    }
}
