package com.tistory.shanepark.heap;

public class MaxHeap {
    public static void main(String[] args) {

        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>((o1, o2) -> Integer.compare(o2,o1));
        maxHeap.add(2);
        maxHeap.add(0);
        maxHeap.add(4);
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());

    }
}
