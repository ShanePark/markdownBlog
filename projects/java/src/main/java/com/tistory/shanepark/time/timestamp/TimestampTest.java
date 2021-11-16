package com.tistory.shanepark.time.timestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimestampTest {
    public static void main(String[] args) {
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }
}
