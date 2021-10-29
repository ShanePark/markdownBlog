package com.tistory.shanepark.heap;

public class PriorityQueue {
    public static void main(String[] args) {

        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        minHeap.add(2);
        minHeap.add(0);
        minHeap.add(4);
        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());

    }
}
