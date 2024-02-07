package com.tistory.shanepark.uuid;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UlidTest {
    static final int LOOP_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        List<String> ulids = new ArrayList<>();
        List<String> uuids = new ArrayList<>();
        for (int i = 0; i < LOOP_COUNT; i++) {
            Ulid ulid = createUlid();
            UUID uuid = ulid.toUuid();
            ulids.add(ulid.toString());
            uuids.add(uuid.toString());
            System.out.printf("ulid = %s, uuid = %s LOOP : %d/%d\n", ulid, uuid, i, LOOP_COUNT);
            Thread.sleep(200);
        }

        // check whether those two lists are sorted
        for (int i = 0; i < LOOP_COUNT - 1; i++) {
            if (ulids.get(i).compareTo(ulids.get(i + 1)) > 0) {
                throw new RuntimeException("ulid is not sorted");
            }
            if (uuids.get(i).compareTo(uuids.get(i + 1)) > 0) {
                throw new RuntimeException("uuid is not sorted");
            }
        }
    }

    private static Ulid createUlid() {
        return UlidCreator.getMonotonicUlid();
    }

}
