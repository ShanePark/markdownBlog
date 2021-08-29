package com.tistory.shanepark;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class Lambda08Supplier2 {

    public static void main(String[] args) {

        List<String> myList = list(ArrayList::new, s -> s.toUpperCase(), "one", "two", "three", "four");
        System.out.println(myList);

    }

    public static <T, R> List<R> list(Supplier<List<R>> factory, Function<T, R> func, T... args) {
        List<R> list = factory.get();
        for (T a : args) {
            list.add(func.apply(a));
        }
        return list;
    }

}
 