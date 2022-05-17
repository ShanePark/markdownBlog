package com.tistory.shanepark.time.yearmonth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

public class YearMonth {

    @Test
    @DisplayName("get first day of month of last year")
    public void test() {
        LocalDate day1 = java.time.YearMonth.now().atDay(1);
        assertThat(day1).isEqualTo(LocalDate.now().withDayOfMonth(1));

        LocalDate minusYear = day1.minusYears(1);
        assertThat(minusYear).isEqualTo(LocalDate.now().minusYears(1).withDayOfMonth(1));

        LocalDateTime startOfDay = minusYear.atStartOfDay();
        assertThat(startOfDay).isEqualTo(LocalDate.now().minusYears(1).withDayOfMonth(1).atStartOfDay());

        Instant instance = startOfDay.toInstant(ZoneOffset.UTC);
        System.out.println("instance = " + instance);
    }
}
