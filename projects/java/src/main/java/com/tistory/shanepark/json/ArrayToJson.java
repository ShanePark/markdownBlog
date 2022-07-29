package com.tistory.shanepark.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ArrayToJson {

    final static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        List<String> servers = List.of("adm", "user", "auth");
        String result = mapper.writeValueAsString(servers);
        System.out.println(result);

        System.out.println(mapper.writeValueAsString(List.of("adm")));

        List<String> back = mapper.readValue(result, List.class);
        System.out.println("back = " + back);
        System.out.println("servers.equals(back) = " + servers.equals(back));
    }
}
