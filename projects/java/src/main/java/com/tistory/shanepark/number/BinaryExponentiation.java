package com.tistory.shanepark.number;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryExponentiation {

    @Test
    @DisplayName("Speed Test")
    void test() {
        StopWatch stopWatch = new StopWatch();
        Set<Integer> answers = new HashSet<>();

        answers.add(measure(new IterateCalculator(), stopWatch));
        answers.add(measure(new BigIntegerCalculator(), stopWatch));
        answers.add(measure(new BinaryExponentiationCalculator(), stopWatch));

        Assertions.assertThat(answers).hasSize(1);
        System.out.println("stopwach = " + stopWatch.prettyPrint());
    }

    @Test
    @DisplayName("Test 2^13")
    void test2() {
        BinaryExponentiationCalculator calc = new BinaryExponentiationCalculator();
        int result = calc.calc(2, 13, 1000000007);
        Assertions.assertThat(result).isEqualTo((int) Math.pow(2, 8) * (int) Math.pow(2, 4) * (int) Math.pow(2, 1));
    }

    private int measure(Calculator calculator, StopWatch stopWatch) {
        final int BASE = 2;
        final int POW = 10000;
        final int MOD = (int) 1e9 + 7;

        stopWatch.start(calculator.getClass().getSimpleName());
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            answers.add(calculator.calc(BASE, POW, MOD));
        }
        stopWatch.stop();
        return answers.get(0);
    }

    interface Calculator {
        int calc(int base, int pow, int mod);
    }

    class IterateCalculator implements Calculator {
        @Override
        public int calc(int base, int pow, int mod) {
            long cur = 1L;
            for (int i = 0; i < pow; i++) {
                cur = (cur * base) % mod;
            }
            return (int) cur;
        }
    }

    class BigIntegerCalculator implements Calculator {
        @Override
        public int calc(int base, int pow, int mod) {
            return BigInteger.valueOf(base)
                    .pow(pow)
                    .mod(BigInteger.valueOf(mod))
                    .intValue();
        }
    }

    class BinaryExponentiationCalculator implements Calculator {
        @Override
        public int calc(int base, int pow, int mod) {
            long result = 1;
            for (; pow > 0; pow /= 2) {
                if (pow % 2 == 1) {
                    result = (result * base) % mod;
                }
                base = (int) ((long) base * base % mod);
            }
            return (int) result;
        }
    }


}
