package com.tistory.shanepark.number;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareBoxedNumbers {
    @Test
    public void test() {

        // You shouldn't compare boxed numbers with `==`
        assertThat(Long.valueOf(1234567) == Long.valueOf(1234567)).isFalse();

        // Null safe way to compare
        assertThat(Objects.equals(Long.valueOf(1234567), Long.valueOf(1234567))).isTrue();

        // some numbers are cached
        assertThat(Long.valueOf(123) == Long.valueOf(123)).isTrue();
    }
}
