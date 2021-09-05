package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Stream03 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,2,5,4,3,6,7,9,1,10};
        List list = Arrays.asList(arr);

        Arrays.sort(arr);
        for(int n : arr){
            System.out.println(n);
        }

        Collections.sort(list);
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
