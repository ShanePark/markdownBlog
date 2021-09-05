package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream06 {
    public static void main(String[] args) {

        Stream<String[]> stream = Stream.of(new String[]{"jenny", "shane"}, new String[]{"game", "programming"});

        stream.flatMap(Arrays::stream).forEach(System.out::println);

    }
}
