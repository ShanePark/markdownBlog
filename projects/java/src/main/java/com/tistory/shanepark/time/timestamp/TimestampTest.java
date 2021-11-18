package com.tistory.shanepark.time.timestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class TimestampTest {
    public static void main(String[] args) {
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
        System.out.println(LocalDateTime.now());
        System.out.println(new Date());
    }
}
