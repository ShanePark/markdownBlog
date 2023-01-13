package com.tistory.shanepark.xml.holiday;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HolidayFinderTest {

    String apiAddress = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
    String serviceKey = "YOUR_SERVICE_KEY";
    XmlParser xmlParser = new XmlParser();

    @Test
    public void findHolidayTest() {
        List<HolidayInfo> holidays = new HolidayFinder(apiAddress, serviceKey, xmlParser)
                .findHolidays(2023);
        assertThat(holidays).hasSize(16);
        assertThat(holidays.get(0).getName()).isEqualTo("1월1일");
        assertThat(holidays.get(0).isHoliday()).isTrue();
        assertThat(holidays.get(0).getYear()).isEqualTo(2023);
        assertThat(holidays.get(0).getMonth()).isEqualTo(1);
        assertThat(holidays.get(0).getDay()).isEqualTo(1);

        assertThat(holidays.stream().filter(h -> h.getMonth() == 10)).hasSize(2);
        Optional<HolidayInfo> koreanDay = holidays.stream()
                .filter(h -> h.getMonth() == 10 && h.getDay() == 9)
                .findAny();
        assertThat(koreanDay).isPresent();
        assertThat(koreanDay.get().getName()).isEqualTo("한글날");
    }

    @Test
    public void holidayFindFailTest() {
        HolidayFinder finder = new HolidayFinder(apiAddress, "wrongKey", xmlParser);
        assertThrows(RuntimeException.class, () -> finder.findHolidays(2023));
    }

    @Test
    public void test() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse("20230101", formatter);
        assertThat(date.getYear()).isEqualTo(2023);
        assertThat(date.getMonthValue()).isEqualTo(1);
    }
}
