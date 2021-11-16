package com.tistory.shanepark.localDateTime;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class WhatDayAndWhatDate {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        Assert.isTrue(today.getDayOfMonth()==16, "날짜 불일치");
        Assert.isTrue(today.getDayOfWeek().toString().equals("TUESDAY"), "요일 불일치");
    }
}
