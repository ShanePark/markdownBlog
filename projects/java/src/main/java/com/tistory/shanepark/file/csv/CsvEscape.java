package com.tistory.shanepark.file.csv;

import org.apache.commons.text.StringEscapeUtils;

public class CsvEscape {

    public static void main(String[] args) {
        String str = "100,000,000원을 \"김두한\"에게 전달해주세요.";
        String escape = StringEscapeUtils.escapeCsv(str);
        System.out.println(escape);
    }
}
