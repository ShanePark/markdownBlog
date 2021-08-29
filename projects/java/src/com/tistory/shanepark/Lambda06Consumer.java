package com.tistory.shanepark;

import java.util.function.Consumer;
import java.util.function.Function;

public class Lambda06Consumer {

    public static void main(String[] args) {
        Consumer<String> printHello = System.out::println;
        Consumer<String> printComma = (s) -> System.out.println(",");

        printHello.accept("Hello world");
    }

}
