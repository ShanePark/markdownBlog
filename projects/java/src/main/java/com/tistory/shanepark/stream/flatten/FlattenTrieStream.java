package com.tistory.shanepark.stream.flatten;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FlattenTrieStream {

    @Test
    public void flattenTest() {
        // Given
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        List<Node> heads = List.of(node2, node1);
        Node node1_1 = node1.addChild("node1_1");
        Node node1_2 = node1.addChild("node1_2");

        // When
        NodeFlatter flatter = new StreamNodeFlatter();
        NodeFlatter flatter2 = new ForNodeFlatter();

        // Then
        assertThat(flatter.flatten(heads).stream()).containsExactlyInAnyOrder(node1, node1_1, node1_2, node2);
        assertThat(flatter2.flatten(heads).stream()).containsExactlyInAnyOrder(node1, node1_1, node1_2, node2);
    }

    interface NodeFlatter {
        List<Node> flatten(List<Node> nodes);
    }

    class StreamNodeFlatter implements NodeFlatter {
        @Override
        public List<Node> flatten(List<Node> nodes) {
            return nodes.stream()
                    .flatMap(node -> Stream.concat(Stream.of(node), node.children.stream()))
                    .collect(Collectors.toList());
        }
    }

    class ForNodeFlatter implements NodeFlatter {
        @Override
        public List<Node> flatten(List<Node> nodes) {
            List<Node> result = new ArrayList<>();
            for (Node node : nodes) {
                result.add(node);
                result.addAll(node.children);
            }
            return result;
        }
    }

    class Node {
        String value;
        List<Node> children = new ArrayList<>();

        public Node(String value) {
            this.value = value;
        }

        Node addChild(String value) {
            Node child = new Node(value);
            children.add(child);
            return child;
        }
    }
}
