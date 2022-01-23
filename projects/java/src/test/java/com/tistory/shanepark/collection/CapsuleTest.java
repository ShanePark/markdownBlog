package com.tistory.shanepark.collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CapsuleTest {

    @Test
    public void testArr() {
        Capsule capsule = new Capsule("hello", "world");

        List<String> list = capsule.getList();
        list.set(0, "modified");
        Assertions.assertThat(capsule.getList()).containsExactly("hello", "world");

        capsule.add("!");
        Assertions.assertThat(capsule.getList()).contains("!");

        capsule.delete("world");
        Assertions.assertThat(capsule.getList()).containsExactly("hello", "!");
    }

}
