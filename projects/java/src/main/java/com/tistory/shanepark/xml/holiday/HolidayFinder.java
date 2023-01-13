package com.tistory.shanepark.xml.holiday;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class HolidayFinder {
    private final String ApiAddress;
    private final String serviceKey;
    private final XmlParser xmlParser;

    public HolidayFinder(String apiAddress, String serviceKey, XmlParser xmlParser) {
        this.ApiAddress = apiAddress;
        this.serviceKey = serviceKey;
        this.xmlParser = xmlParser;
    }

    public List<HolidayInfo> findHolidays(int year) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = ApiAddress + "?ServiceKey=" + serviceKey + "&solYear=" + year + "&numOfRows=100";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error while calling API: " + response.body());
            }
            XmlResult xmlResult = xmlParser.parse(response.body());
            if (!xmlResult.isSuccess() || xmlResult.getItems() == null)
                throw new RuntimeException("Api call was successful but error occurred: " + xmlResult.getErrorMessage());
            return xmlResult.getItems().stream()
                    .map(item -> HolidayInfo.of(item))
                    .collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while calling API", e);
        }
    }

}
