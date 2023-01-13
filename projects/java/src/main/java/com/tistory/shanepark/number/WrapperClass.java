package com.tistory.shanepark.number;

import java.util.ArrayList;
import java.util.List;

public class WrapperClass {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // Before Java 5
        Integer one = Integer.valueOf(1);
        list.add(one);

        // After Java 5
        list.add(1);
    }

}
