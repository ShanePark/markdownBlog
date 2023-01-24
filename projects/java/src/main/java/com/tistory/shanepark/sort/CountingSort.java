package com.tistory.shanepark.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 대한민국에는 총 5천만명의 국민들이 살고 있습니다. 국민 전체의 형제자매 수 데이터가 int 배열로 주어집니다. `int[] siblings`
 * <p>
 * 티비에 나왔던 중랑구의 14남매가 가장 많은 형제자매라는 정보가 주어졌을 때,
 * 형제자매 숫자로 오름차순 정렬된 길이 50,000,000의 int 배열을 반환하는 `sortSiblings(int[] siblings)` 메서드를 구현 해 보세요.
 */
public class CountingSort {

    final int POPULATION = 50_000_000;
    final int MAX = 14;

    @Test
    public void countSortTest() {
        int[] data = {1, 5, 5, 4, 4, 3, 3};
        countingSort.accept(data);
        System.out.println(Arrays.toString(data));
        assertThat(data).isSorted();
    }

    @Test
    public void compare() {
        int[] siblings = randomArray();
        long start = System.currentTimeMillis();
        sortSiblings(siblings, (Consumer<int[]>) arr -> Arrays.sort(arr));
        System.out.println("regular Sort" + ": " + (System.currentTimeMillis() - start) + "ms");
        assertThat(siblings).isSorted();

        siblings = randomArray();
        start = System.currentTimeMillis();
        sortSiblings(siblings, countingSort);
        System.out.println("CountingSort sort" + ": " + (System.currentTimeMillis() - start) + "ms");
        assertThat(siblings).isSorted();

        siblings = randomArray();
        start = System.currentTimeMillis();
        sortSiblings(siblings, myCountingSort);
        System.out.println("myCountingSort sort" + ": " + (System.currentTimeMillis() - start) + "ms");
        assertThat(siblings).isSorted();
    }

    Consumer<int[]> countingSort = arr -> {
        int n = arr.length;

        int sorted[] = new int[n];
        int count[] = new int[MAX];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; ++i) {
            count[arr[i]]++;
        }

        for (int i = 1; i < MAX; ++i)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            sorted[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        for (int i = 0; i < n; ++i)
            arr[i] = sorted[i];
    };

    Consumer<int[]> myCountingSort = arr -> {
        int[] counts = new int[MAX];
        for (int i : arr) {
            counts[i]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            for (int j = 0; j < count; j++) {
                arr[index++] = i;
            }
        }
    };

    int[] sortSiblings(int[] siblings, Consumer sorter) {
        sorter.accept(siblings);
        return siblings;
    }

    private int[] randomArray() {
        int[] arr = new int[POPULATION];
        for (int i = 0; i < POPULATION; i++) {
            arr[i] = new Random().nextInt(MAX);
        }
        return arr;
    }

}
