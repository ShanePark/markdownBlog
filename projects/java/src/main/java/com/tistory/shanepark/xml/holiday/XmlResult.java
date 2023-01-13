package com.tistory.shanepark.xml.holiday;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class XmlResult {

    // common
    private final String resultCode;
    private final boolean isSuccess;

    // success
    private final int numOfRows;
    private final int pageNo;
    private final int totalCount;
    private final List<Map<String, String>> items;

    // error
    private final String errorMessage;

    static public XmlResult ofSuccess(String resultCode, int numOfRows, int pageNo, int totalCount, List<Map<String, String>> items) {
        return new XmlResult(resultCode, true, numOfRows, pageNo, totalCount, items, null);
    }

    static public XmlResult ofFail(String message, String code) {
        return new XmlResult(code, false, 0, 0, 0, null, message);
    }

    @Override
    public String toString() {
        return "XmlResult{" +
                "resultCode='" + resultCode + '\'' +
                ", isSuccess=" + isSuccess +
                ", numOfRows=" + numOfRows +
                ", pageNo=" + pageNo +
                ", totalCount=" + totalCount +
                ", items=" + items +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
