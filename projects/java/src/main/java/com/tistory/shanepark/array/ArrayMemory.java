package com.tistory.shanepark.array;

public class ArrayMemory {

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[Integer.MAX_VALUE - 2];
        System.out.println("배열 생성 성공 + arr.length: " + arr.length);
        // it only works when have enought Heap Memory
        // java -Xms9g ./ArrayMemory.java
    }
}
