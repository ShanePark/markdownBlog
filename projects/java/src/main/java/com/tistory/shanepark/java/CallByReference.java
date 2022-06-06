package com.tistory.shanepark.java;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.assertj.core.api.Assertions.assertThat;

public class CallByReference {

    @Test
    public void changeMacTest() {
        Mac myMac = new Mac("Macbook Air", "m1", 13, 8);
        Mac macBookPro14 = new Mac("Macbook Pro", "m1 max", 14, 32);

        System.out.println("==BEFORE==");
        System.out.println("myMac = " + myMac);
        System.out.println("macBookPro14 = " + macBookPro14);
        changeMac(myMac, macBookPro14);

        System.out.println("==AFTER==");
        System.out.println("myMac = " + myMac);
        System.out.println("macBookPro14 = " + macBookPro14);
        Assertions.assertThrows(AssertionFailedError.class, () -> {
                    assertThat(myMac).isEqualTo(macBookPro14);
                }
        );

    }

    @Test
    public void upgradeRamTest() {
        Mac myMac = new Mac("Macbook Air", "m1", 13, 8);
        upgradeRam(myMac, 32);
        assertThat(myMac.getRamSize()).isEqualTo(32);
        System.out.println("myMac = " + myMac);
    }

    @Test
    public void changeMacAndChangeRamTest() {
        Mac myMac = new Mac("Macbook Air", "m1", 13, 8);
        Mac macBookPro14 = new Mac("Macbook Pro", "m1 max", 14, 32);

        System.out.println("==BEFORE==");
        System.out.println("myMac = " + myMac);
        System.out.println("macBookPro14 = " + macBookPro14);
        changeMacAndChangeRam(myMac, macBookPro14, 4);

        System.out.println("==AFTER==");
        System.out.println("myMac = " + myMac);
        System.out.println("macBookPro14 = " + macBookPro14);
    }

    private void changeMac(Mac myMac, Mac newMac) {
        myMac = newMac;
    }

    private void changeMacAndChangeRam(Mac myMac, Mac newMac, int ramSize) {
        myMac = newMac;
        myMac.setRamSize(ramSize);
    }

    private void upgradeRam(Mac mac, int size) {
        mac.setRamSize(size);
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
