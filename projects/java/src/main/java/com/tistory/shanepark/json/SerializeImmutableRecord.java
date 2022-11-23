package com.tistory.shanepark.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SerializeImmutableRecord {

    @Test
    public void test() throws JsonProcessingException {
        ImmutableRecord record = new ImmutableRecord("Shane", 100);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(record);
        Assertions.assertThat(json).isEqualTo("{\"name\":\"Shane\",\"hp\":100}");

        ImmutableRecord recordFromJson = objectMapper.readValue(json, ImmutableRecord.class);
        Assertions.assertThat(recordFromJson).isEqualTo(record);
    }

    public record ImmutableRecord(String name, int hp) {
    }

}
