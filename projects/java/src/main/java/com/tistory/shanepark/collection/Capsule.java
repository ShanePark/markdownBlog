package com.tistory.shanepark.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Capsule {
    private final List<String> list;

    Capsule(String... values) {
        this.list = Arrays.stream(values).collect(Collectors.toList());
    }

    public List<String> getList() {
        return new ArrayList<>(list);
    }

    public void add(String str) {
        this.list.add(str);
    }

    public void delete(String str) {
        this.list.remove(str);
    }
}
