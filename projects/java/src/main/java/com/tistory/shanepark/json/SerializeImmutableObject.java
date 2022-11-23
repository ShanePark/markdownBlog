package com.tistory.shanepark.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class SerializeImmutableObject {

    @Test
    public void test() throws JsonProcessingException {
        ImmutableObject immutableObject = new ImmutableObject("Shane", 100);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(immutableObject);
        Assertions.assertThat(json).isEqualTo("{\"name\":\"Shane\",\"hp\":100}");

        ImmutableObject objectFromJson = objectMapper.readValue(json, ImmutableObject.class);
        Assertions.assertThat(objectFromJson).isEqualTo(immutableObject);
    }

    @Getter
    final static public class ImmutableObject {
        private final String name;
        private final int hp;

        @JsonCreator
        public ImmutableObject(
                @JsonProperty("name") String name,
                @JsonProperty("hp") int hp) {
            this.name = name;
            this.hp = hp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ImmutableObject that)) return false;
            return hp == that.hp && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, hp);
        }
    }

}
