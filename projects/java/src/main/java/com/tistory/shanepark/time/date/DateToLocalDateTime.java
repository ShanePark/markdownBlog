package com.tistory.shanepark.time.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class DateToLocalDateTime {

    @Test
    @DisplayName("Date -> LocalDateTime")
    public void DateToLocalDateTime() {
        Date now = new Date();
        LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        // year
        assertThat(cal.get(Calendar.YEAR)).isEqualTo(localDateTime.getYear());
        // month
        assertThat(cal.get(Calendar.MONTH) + 1).isEqualTo(localDateTime.getMonth().getValue());
        // date
        assertThat(cal.get(Calendar.DATE)).isEqualTo(localDateTime.getDayOfMonth());
        // ms
        assertThat(now.getTime()).isEqualTo(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @Test
    @DisplayName("LocalDateTime -> Date")
    public void LocalDateTimeToDate() {
        LocalDateTime now = LocalDateTime.now();

        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // year
        assertThat(cal.get(Calendar.YEAR)).isEqualTo(now.getYear());
        // month
        assertThat(cal.get(Calendar.MONTH) + 1).isEqualTo(now.getMonth().getValue());
        // date
        assertThat(cal.get(Calendar.DATE)).isEqualTo(now.getDayOfMonth());
        // ms
        assertThat(date.getTime()).isEqualTo(now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

}
