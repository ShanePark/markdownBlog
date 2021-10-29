package com.tistory.shanepark.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Person{
    int number;
    String name;
    Person(int number, String name){
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Stream08Collect2 {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1,"김두한"));
        list.add(new Person(2,"시라소니"));

        Map<Integer, Person> map = list.stream().collect(Collectors.toMap(p->p.number, p->p));

        System.out.println(map.get(2));

    }
}
