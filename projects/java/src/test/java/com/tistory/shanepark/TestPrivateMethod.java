package com.tistory.shanepark;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPrivateMethod {
    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Capsule capsule = new Capsule();
        Method method = Capsule.class.getDeclaredMethod("returnOne");
        method.setAccessible(true);
        assertThat(method.invoke(capsule)).isEqualTo(1);
    }

    @Test
    public void paraTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Capsule capsule = new Capsule();
        Method method = Capsule.class.getDeclaredMethod("echo", String.class);
        method.setAccessible(true);
        assertThat(method.invoke(capsule, "Hello test")).isEqualTo("Hello test");
    }

    @Test
    public void reflectionTestUtils() {
        Capsule capsule = new Capsule();
        assertThat(ReflectionTestUtils.<String>invokeMethod(capsule, "echo", "test123")).isEqualTo("test123");
    }
}

class Capsule {
    private int returnOne() {
        return 1;
    }

    private String echo(String str) {
        return str;
    }
}
