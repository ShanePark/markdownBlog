package com.tistory.shanepark.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveValueFromMap {

    @Test
    public void test() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.values().remove("one");

        assertThat(map.values()).hasSize(2);
        assertThat(map).hasSize(2);
    }

    @Test
    public void test2() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(-1, "one");

        Set<Integer> deleteKeys = new HashSet<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if ("one".equals(entry.getValue())) {
                deleteKeys.add(entry.getKey());
            }
        }

        deleteKeys.stream().forEach(k -> map.remove(k));

        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    public void test3() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(-1, "one");

        map.entrySet().stream()
                .filter(e -> "one".equals(e.getValue()))
                .collect(Collectors.toUnmodifiableSet())
                .forEach(e -> map.remove(e.getKey()));

        assertThat(map.size()).isEqualTo(2);
    }
}
