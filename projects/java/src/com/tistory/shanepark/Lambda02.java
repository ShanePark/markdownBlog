package com.tistory.shanepark;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lambda02 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "aaa", "bbb", "ddd", "aaa");
        // 내림차순
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list);

        // 오름차순
        Collections.sort(list, (s1,s2) -> s1.compareTo(s2));
        System.out.println(list);
    }

}
