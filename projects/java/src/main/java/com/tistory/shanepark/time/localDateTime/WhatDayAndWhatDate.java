package com.tistory.shanepark.time.localDateTime;

import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

public class WhatDayAndWhatDate {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        Assertions.assertTrue(today.getDayOfMonth() == 17, "날짜 불일치");
        Assertions.assertTrue(today.getDayOfWeek().toString().equals("WEDNESDAY"), "요일 불일치");
        System.out.println(LocalDateTime.now().minusDays(3).getDayOfWeek().getValue()); // 월요일 1 부터 시작, 일요일은 7
    }
}
