package com.tistory.shanepark.regex;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

import static org.assertj.core.api.Assertions.assertThat;


public class AntPattern {

    @Test
    public void antTest() {
        assertThat(matches("/static/**", "/static/hi")).isTrue();
        assertThat(matches("/static/**", "/statics/hi")).isFalse();

        String ant = "/data/*";
        assertThat(matches(ant, "/data/ee3ee40d-5de8-4f9c-ad27-c15a8d934ec6")).isTrue();
        assertThat(matches(ant, "/data/workflow/")).isFalse();

    }

    public static boolean matches(String pattern, String input) {
        return new AntPathMatcher().match(pattern, input);
    }

}
