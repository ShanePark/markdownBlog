package com.tistory.shanepark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Lambda07Predicate {

    public static void main(String[] args) {
        Predicate<String> isEmpty = (s) -> s == null || s.replace(" ", "").length() == 0;
        System.out.println(isEmpty.test(" "));
        System.out.println(isEmpty.test("abc"));

        String[] arr = new String[]{"abc", "  ", "dcf"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, arr);
        removeEmpty(list, isEmpty);
        System.out.println(list);

    }

    public static <T> void removeEmpty(List<T> list, Predicate<T> filter) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (filter.test(t))
                iterator.remove();
        }
    }

}
 