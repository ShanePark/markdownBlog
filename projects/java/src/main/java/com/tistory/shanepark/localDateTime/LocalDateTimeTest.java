package com.tistory.shanepark.localDateTime;

import java.time.LocalDateTime;

// LocalDateTime 공부 및 확인 용도
public class LocalDateTimeTest {

    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.now();
        System.out.println(date1.plusMonths(3));
    }

}
