package com.tistory.shanepark.time.advanced;

public class MillisecondsToHMS {
    public static void main(String[] args) {
        long totalTime = (long)2.1e+6+1200;
        long hour = totalTime / (60 * 60 * 1000);
        long min = (totalTime % (60 * 60 * 1000)) / (60 * 1000);
        long sec = (totalTime % (60 * 1000)) / 1000;
        System.out.printf("%d시간 %d분 %d초", hour, min, sec);
    }
}
