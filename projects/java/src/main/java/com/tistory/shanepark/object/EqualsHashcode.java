package com.tistory.shanepark.object;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsHashcode {

    @Test
    void test() {
        MyPair a = new MyPair(1, 2);
        MyPair b = new MyPair(2, 1);

        Set<MyPair> set = new HashSet<>();
        set.add(a);
        set.add(b);
        Assertions.assertThat(a).isEqualTo(b);
        Assertions.assertThat(a.equals(b)).isTrue();
        Assertions.assertThat(set.size()).isEqualTo(1);
        Assertions.assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    private class MyPair {
        int num1;
        int num2;
        private Integer hashCode;

        public MyPair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public boolean equals(Object o) {
            MyPair p = (MyPair) o;
            if (p.num1 == num1 && p.num2 == num2) {
                return true;
            } else if (p.num1 == num2 && p.num2 == num1) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            if (this.hashCode == null)
                this.hashCode = Objects.hash((num1 + num2) * num1 * num2);
            return this.hashCode;
        }
    }

}
