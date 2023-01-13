package com.tistory.shanepark.number;

import org.springframework.util.StopWatch;

import java.util.List;

/**
 * -------------------------------
 * TaskName = primitive
 * Total Execution time = 649ms
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
        final int LOOP_SIZE = Integer.MAX_VALUE;

        stopWatch.start("primitive");
        int sum = 0;
        for (int i = 0; i < LOOP_SIZE; i++) {
            sum += i;
        }
        stopAndPrintLastTask(stopWatch);
        stopWatch.start("autoBoxing");
        Integer num = null;
        for (int i = 0; i < LOOP_SIZE; i++) {
            num = i;
        }
        stopAndPrintLastTask(stopWatch);

        stopWatch.start("valueOf");
        for (int i = 0; i < LOOP_SIZE; i++) {
            num = Integer.valueOf(i);
        }
        stopAndPrintLastTask(stopWatch);

        sum = 0;

        stopWatch.start("Unboxing");
        for (Integer i = 0; i < LOOP_SIZE; i++) {
            sum += i;
        }
        stopAndPrintLastTask(stopWatch);

        List.of(sum, num); // Consume variables. otherwise compiler will optimize it and skip the loop.
    }

    static void stopAndPrintLastTask(StopWatch stopWatch) {
        stopWatch.stop();
        StopWatch.TaskInfo task = stopWatch.getLastTaskInfo();
        System.out.println("----------------------------");
        System.out.println("TaskName = " + task.getTaskName());
        System.out.println("Total Execution time = " + task.getTimeMillis() + "ms\n");
    }

}
