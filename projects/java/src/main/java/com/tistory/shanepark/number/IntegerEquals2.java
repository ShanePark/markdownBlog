package com.tistory.shanepark.number;

public class IntegerEquals2 {
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            Integer i1 = Integer.valueOf(i);
            Integer i2 = Integer.valueOf(i);
            boolean same = i1 == i2;
            boolean same2 = Integer.compare(i1, i2) == 0;
            System.out.printf("i1=%3d, i2=%3d, same = %s, same2 = %s\n", i1, i2, same, same2);
        }
    }
}
