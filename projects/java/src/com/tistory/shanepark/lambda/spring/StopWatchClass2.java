package com.tistory.shanepark.lambda.spring;

import org.springframework.util.StopWatch;

public class StopWatchClass2 {
    static final int number = 45;
    static long[] memo= new long[number+1];

    public static void main(String[] args) {

        StopWatch stopwatch = new StopWatch("fibonacci");

        // 1번 작업
        stopwatch.start("recursive");
        fibonacci(number);
        stopwatch.stop();

        // 2번 작업
        stopwatch.start("DP");
        fibonacciDp(number);
        stopwatch.stop();

        // 결과 분석
        System.out.println(stopwatch.prettyPrint());

    }

    public static long fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static long fibonacciDp(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        if(memo[n] != 0) {
            return memo[n];
        }else {
            return memo[n] = fibonacciDp(n-1) + fibonacciDp(n-2);
        }
    }

}