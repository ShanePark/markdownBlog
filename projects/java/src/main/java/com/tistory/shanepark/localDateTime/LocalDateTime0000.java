package com.tistory.shanepark.localDateTime;

import java.time.LocalDateTime;

// LocalDateTime 시,분,초 제거
public class LocalDateTime0000 {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now().withHour(23).withMinute(59).withSecond(0).withNano(0);
        System.out.println(date);
    }

}
