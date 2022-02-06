package com.tistory.shanepark.string;

public class Immutable {
    public static void main(String[] args) {
        String one = "1";
        String temp = "1";
        String first = "1";
        String newOne = new String("1");

        printRef(one);
        printRef(temp);
        printRef(first);
        printRef(newOne);

        System.out.println("\none == temp : " + (one == temp));
        System.out.println("temp == first : " + (temp == first));
        System.out.println("first == newOne : " + (first == newOne));
    }

    static void printRef(String str) {
        System.out.println("value: " + str + ", ref: " + Integer.toHexString(System.identityHashCode(str)));
    }

}
