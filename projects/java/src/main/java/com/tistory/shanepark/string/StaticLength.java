package com.tistory.shanepark.string;

import java.util.ArrayList;
import java.util.List;

public class StaticLength {

    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100_000_000; i++) {
            sb.append('a');
        }

        String str = sb.toString();
        Character c;

        long start;

        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();

        for (int j = 0; j < 100; j++) {
            start = System.currentTimeMillis();
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
            }
            list1.add(System.currentTimeMillis() - start);

            start = System.currentTimeMillis();
            final int length = str.length();
            for (int i = 0; i < length; i++) {
                c = str.charAt(i);
            }
            list2.add(System.currentTimeMillis() - start);

            System.out.println(j + "번째 테스트 진행중...");
        }

        list1.stream().mapToLong(l -> l).average().ifPresent(avg -> System.out.println(".length()의 평균 소요시간: " + avg));
        list2.stream().mapToLong(l -> l).average().ifPresent(avg -> System.out.println("사이즈 고정시 평균 소요시간: " + avg));

    }
}
