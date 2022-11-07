package com.tistory.shanepark.stream.flatten;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenCollectionStream {

    @Test
    public void flattenCollection() {
        // Given
        String SUB1_TXT1 = "sub1_txt1";
        String SUB1_TXT2 = "sub1_txt2";
        String SUB2_TXT1 = "sub2_txt1";
        String SUB2_TXT2 = "sub2_txt2";

        List<List<String>> list = new ArrayList<>();

        ArrayList<String> sub1 = new ArrayList<>();
        sub1.add(SUB1_TXT1);
        sub1.add(SUB1_TXT2);

        ArrayList<String> sub2 = new ArrayList<>();
        sub1.add(SUB2_TXT1);
        sub1.add(SUB2_TXT2);

        list.add(sub1);
        list.add(sub2);

        // When
        CollectionFlatter flatter1 = new CollectionStreamFlatter();
        Collection flatData1 = flatter1.flatten(list);

        CollectionFlatter flatter2 = new CollectionForFlatter();
        Collection flatData2 = flatter2.flatten(list);

        // Then
        Assertions.assertThat(flatData1).containsExactlyInAnyOrder(SUB1_TXT1, SUB1_TXT2, SUB2_TXT1, SUB2_TXT2);
        Assertions.assertThat(flatData2).containsExactlyInAnyOrder(SUB1_TXT1, SUB1_TXT2, SUB2_TXT1, SUB2_TXT2);
    }

    class CollectionStreamFlatter<T> implements CollectionFlatter<T> {
        @Override
        public Collection flatten(Collection<Collection<T>> collection) {
            return collection.stream()
                    .flatMap(c -> c.stream())
                    .collect(Collectors.toList());
        }
    }

    class CollectionForFlatter<T> implements CollectionFlatter<T> {
        @Override
        public Collection flatten(Collection<Collection<T>> collection) {
            Collection<T> result = new ArrayList<>();
            for (Collection<T> subCollection : collection) {
                for (T t : subCollection) {
                    result.add(t);
                }
            }
            return result;
        }
    }

    interface CollectionFlatter<T> {
        Collection<T> flatten(Collection<Collection<T>> collection);
    }

}
