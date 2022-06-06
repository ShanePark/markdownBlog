package com.tistory.shanepark.java;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CallByReferenceThis {

    Mac myMac = new Mac("Macbook Air", "m1", 13, 8);

    @Test
    public void changeMacTest() {
        Mac macBookPro14 = new Mac("Macbook Pro", "m1 max", 14, 32);
        changeMac(macBookPro14);
        assertThat(myMac).isEqualTo(macBookPro14);
    }

    private void changeMac(Mac newMac) {
        this.myMac = newMac;
    }


    @Setter
    @Getter
    class Mac {
        private final String name;
        private final String processor;
        private int screenSize;
        private int ramSize;

        public Mac(String name, String processor, int screenSize, int ramSize) {
            this.name = name;
            this.processor = processor;
            this.screenSize = screenSize;
            this.ramSize = ramSize;
        }

        @Override
        public String toString() {
            return "Mac{" +
                    "name='" + name + '\'' +
                    ", processor='" + processor + '\'' +
                    ", screenSize=" + screenSize +
                    ", ramSize=" + ramSize + "GB" +
                    '}';
        }
    }

}
