package com.tistory.shanepark.experiment.probability;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.*;

class PokemonStickerTest {

    final int PRICE = 1500;

    @Test
    void testBuyPokemon() {
        int[] arr = new int[160];
        for (int i = 0; i < 1_000_000; i++) {
            arr[buyPokemon()]++;
        }
        for (int i = 1; i <= 159; i++) {
            System.out.print(arr[i] + " ");
            if (i % 10 == 0)
                System.out.println();
        }
        Assertions.assertThat(arr[0]).isEqualTo(0);
        Assertions.assertThat(Arrays.stream(arr).sum()).isEqualTo(1_000_000);
    }

    @Test
    void test151() {
        List<Integer> result = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int TRY = 100000;
        for (int i = 0; i < TRY; i++) {
            Set<Integer> pokeDex = new HashSet<>();
            int cnt = 0;
            while (pokeDex.size() < 151) {
                pokeDex.add(buyPokemon() % 151);
                cnt++;
            }
            result.add(cnt);
        }

        int max = result.stream().mapToInt(Integer::intValue).max().getAsInt();
        int min = result.stream().mapToInt(Integer::intValue).min().getAsInt();
        double avg = result.stream().mapToInt(Integer::intValue).average().getAsDouble();
        stopWatch.stop();

        System.out.println("max = " + max);
        System.out.println("min = " + min);
        System.out.println("avg = " + avg);
        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());

    }

    @Test
    void test159() {
        List<Integer> result = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int TRY = 1_000_000;
        for (int i = 0; i < TRY; i++) {
            Set<Integer> pokeDex = new HashSet<>();
            int cnt = 0;
            while (pokeDex.size() < 159) {
                pokeDex.add(buyPokemon());
                cnt++;
            }
            result.add(cnt);
        }

        stopWatch.stop();

        System.out.println("max = " + result.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println("min = " + result.stream().mapToInt(Integer::intValue).min().getAsInt());
        System.out.println("avg = " + result.stream().mapToInt(Integer::intValue).average().getAsDouble());
        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());

    }

    @Test
    public void testSpecial() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= 155; i++) {
            for (int j = 0; j < 10000; j++) {
                stack.add(i);
            }
        }
        for (int i = 156; i <= 159; i++) {
            for (int j = 0; j < 1000; j++) {
                stack.add(i);
            }
        }
        Assertions.assertThat(stack.size()).isEqualTo(1554000);
        Collections.shuffle(stack);

        final int PEOPLE = 1000;
        List<Integer> result = new ArrayList<>();
        loop:for (int i = 0; i < PEOPLE; i++) {
            Set<Integer> pokeDex = new HashSet<>();
            int cnt = 0;
            while (pokeDex.size() < 159) {
                try {
                    pokeDex.add(stack.pop());
                } catch (EmptyStackException e) {
                    System.out.println((i+1) + "번째 사람이 살 빵이 더이상 남아있지 않습니다.");
                    break loop;
                }
                cnt++;
            }

            result.add(cnt);
        }

        System.out.println("max = " + result.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println("min = " + result.stream().mapToInt(Integer::intValue).min().getAsInt());
        System.out.println("avg = " + result.stream().mapToInt(Integer::intValue).average().getAsDouble());

    }

    private static int buyPokemon() {
        return (int) (Math.random() * 159) + 1;
    }

}
