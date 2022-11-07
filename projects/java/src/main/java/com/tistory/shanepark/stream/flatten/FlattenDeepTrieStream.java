package com.tistory.shanepark.stream.flatten;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FlattenDeepTrieStream {

    @Test
    public void deepFlattenTest() {
        // Given
        List<Node> heads = new ArrayList<>();
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        heads.add(node2);
        heads.add(node1);

        Node node1_1 = node1.addChild("node1_1");
        Node node1_2 = node1.addChild("node1_2");

        Node node1_1_1 = node1_1.addChild("node1_1_1");
        Node node1_1_2 = node1_1.addChild("node1_1_2");

        Node node1_1_1_1 = node1_1_1.addChild("node1_1_1_1");
        Node node1_1_1_2 = node1_1_1.addChild("node1_1_1_2");

        Node node1_2_1 = node1_2.addChild("node1_2_1");
        Node node1_2_2 = node1_2.addChild("node1_2_2");

        // When
        NodeFlatter flatter = new StreamNodeFlatter();
        NodeFlatter flatter2 = new ForNodeFlatter();

        // When
        assertThat(flatter2.flatten(heads).stream())
                .containsExactlyInAnyOrder(node1, node1_1, node1_1_1, node1_1_1_1, node1_1_1_2, node1_1_2, node1_2, node1_2_1, node1_2_2, node2);
        assertThat(flatter.flatten(heads).stream())
                .containsExactlyInAnyOrder(node1, node1_1, node1_1_1, node1_1_1_1, node1_1_1_2, node1_1_2, node1_2, node1_2_1, node1_2_2, node2);
    }

    interface NodeFlatter {
        List<Node> flatten(List<Node> nodes);
    }

    class StreamNodeFlatter implements NodeFlatter {
        @Override
        public List<Node> flatten(List<Node> nodes) {
            return nodes.stream()
                    .flatMap(node -> flatStream(node))
                    .collect(Collectors.toList());
        }

        private Stream<Node> flatStream(Node node) {
            return Stream.concat(Stream.of(node), flatten(node.children).stream());
        }
    }

    class ForNodeFlatter implements NodeFlatter {
        @Override
        public List<Node> flatten(List<Node> nodes) {
            List<Node> result = new ArrayList<>();
            for (Node node : nodes) {
                result.add(node);
                result.addAll(flatten(node.children));
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
