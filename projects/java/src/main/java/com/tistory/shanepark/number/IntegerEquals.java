package com.tistory.shanepark.number;

public class IntegerEquals {
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            Integer i1 = i;
            Integer i2 = i;
            System.out.printf("i1=%3d, i2=%3d, (i1==i2) = %s\n", i1, i2, (i1 == i2));
        }
    }
}
