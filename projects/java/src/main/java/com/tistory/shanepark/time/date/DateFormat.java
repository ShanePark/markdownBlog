package com.tistory.shanepark.time.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static void main(String[] args) throws ParseException {
        String str = "2021-02-17 16:23:50";
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
        Date date = timeFormat.parse(str);
        System.out.println(date);
    }

}
