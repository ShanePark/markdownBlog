package com.tistory.shanepark.lambda.spring;

import org.springframework.util.StopWatch;

public class StopWatchClass {
    public static void main(String[] args) {

        // StopWatch 생성
        StopWatch stopwatch = new StopWatch();

        // 타이머 시작
        stopwatch.start();

        // 작업
        fibonacci(45);

        // 타이머 종료
        stopwatch.stop();

        // 결과 분석
        System.out.println(stopwatch.prettyPrint());

    }

    public static int fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
