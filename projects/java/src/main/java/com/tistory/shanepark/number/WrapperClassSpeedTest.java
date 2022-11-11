package com.tistory.shanepark.number;

import org.springframework.util.StopWatch;

import java.util.List;

/**
 * -------------------------------
 * TaskName = primitive
 * Total Execution time = 2ms
 * <p>
 * -------------------------------
 * TaskName = autoBoxing
 * Total Execution time = 5179ms
 * <p>
 * -------------------------------
 */
public class WrapperClassSpeedTest {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        int loopCount = Integer.MAX_VALUE;

        stopWatch.start("primitive");
        int n = 0;
        for (int i = 0; i < loopCount; i++) {
            n = i;
        }
        stopAndPrintLastTask(stopWatch);

        stopWatch.start("autoBoxing");
        Integer num = null;
        for (int i = 0; i < loopCount; i++) {
            num = i;
        }
        stopAndPrintLastTask(stopWatch);

        stopWatch.start("valueOf");
        for (int i = 0; i < loopCount; i++) {
            num = Integer.valueOf(i);
        }
        stopAndPrintLastTask(stopWatch);

        stopWatch.start("Unboxing");
        for (Integer i = 0; i < loopCount; i++) {
            n = i;
        }
        stopAndPrintLastTask(stopWatch);

        List.of(n, num); // Otherwise compiler will optimize it and skip the loop.
    }

    static void stopAndPrintLastTask(StopWatch stopWatch) {
        stopWatch.stop();
        StopWatch.TaskInfo task = stopWatch.getLastTaskInfo();
        System.out.println("-------------------------------");
        System.out.println("TaskName = " + task.getTaskName());
        System.out.println("Total Execution time = " + task.getTimeMillis() + "ms\n");
    }

}
