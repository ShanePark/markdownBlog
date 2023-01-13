package com.tistory.shanepark.xml.holiday;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
final class HolidayInfo {
    private final String name;
    private final boolean isHoliday;
    private final int year;
    private final int month;
    private final int day;

    private HolidayInfo(Map<String, String> raw) {
        this.name = raw.get("dateName");
        this.isHoliday = "Y".equals(raw.get("isHoliday"));
        String date = raw.get("locdate");
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.year = localDate.getYear();
        this.month = localDate.getMonthValue();
        this.day = localDate.getDayOfMonth();
    }

    static HolidayInfo of(Map<String, String> raw) {
        return new HolidayInfo(raw);
    }

    @Override
    public String toString() {
        return "HolidayInfo{" +
                "name='" + name + '\'' +
                ", isHoliday=" + isHoliday +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

}
