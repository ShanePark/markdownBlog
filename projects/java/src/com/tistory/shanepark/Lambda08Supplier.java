package com.tistory.shanepark;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda08Supplier {

    public static void main(String[] args) {
        Supplier<String> uuidGenerator = () -> UUID.randomUUID().toString();

        System.out.println(uuidGenerator.get());
        System.out.println(uuidGenerator.get());
        System.out.println(uuidGenerator.get());

    }

}
 