package com.tistory.shanepark.set;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class TreeSetTest {
    User alice = new User("Alice", 30);
    User bob = new User("Bob", 25);
    User charlie = new User("Charlie", 25);
    User dave = new User("Dave", 28);

    @Test
    public void hashSetTest() {
        Set<User> hashSet = new HashSet<>();

        hashSet.add(alice);
        hashSet.add(bob);
        hashSet.add(charlie);
        hashSet.add(dave);

        assertThat(hashSet.size()).isEqualTo(4);
    }

    @Test
    public void treeSetTest() {
        Set<User> treeSet = new TreeSet<>();

        treeSet.add(alice);
        treeSet.add(bob);
        treeSet.add(charlie);
        treeSet.add(dave);

        for (User user : treeSet) {
            System.out.println(user);
        }

        assertThat(treeSet.size()).isEqualTo(4);
    }

    static class User implements Comparable<User> {
        private final UUID id = UUID.randomUUID();
        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User user)) return false;
            return Objects.equals(id, user.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        @Override
        public int compareTo(User other) {
            int ageComparison = Integer.compare(this.age, other.age);
            if (ageComparison != 0) {
                return ageComparison;
            }
            return this.id.compareTo(other.id);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
