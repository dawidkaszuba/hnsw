package pl.dawidkaszuba.model;

import org.junit.jupiter.api.Test;
import pl.dawidkaszuba.distance.DistanceFunction;
import pl.dawidkaszuba.distance.impl.CosineSimilarityDistance;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    DistanceFunction distanceFunction = new CosineSimilarityDistance();

    @Test
    void shouldNotAdd4thNeighbour() {
        //given
        double[] vector0 = {1.1, 2.0, 3.0};

        double[] vector1 = {1.0, 2.0, 3.0};
        double[] vector2 = {2.0, 3.0, 4.0};
        double[] vector3 = {3.0, 4.0, 5.0};
        double[] vector4 = {1000.0, 4000.0, 5000.0};

        Node node1 = new Node(1L, vector1, 0, 3);
        Node node2 = new Node(2L, vector2, 0, 3);
        Node node3 = new Node(3L, vector3, 0, 3);
        Node node4 = new Node(4L, vector4, 0, 3);

        Node nodeToAddNeighbour = new Node(1L, vector0, 0, 3);

        nodeToAddNeighbour.addNeighbor(0, node1, distanceFunction);
        nodeToAddNeighbour.addNeighbor(0, node2, distanceFunction);
        nodeToAddNeighbour.addNeighbor(0, node3, distanceFunction);

        //when
        nodeToAddNeighbour.addNeighbor(0, node4, distanceFunction);

        //then
        assertEquals(3, nodeToAddNeighbour.getNeighbors(0).size());
        assertFalse(nodeToAddNeighbour.getNeighbors(0).contains(node4));
    }

    @Test
    void shouldExchangeWorseNeighbourForBetter() {
        //given
        double[] vector0 = {1.1, 2.0, 3.0};
        Node nodeToAddNeighbour = new Node(1L, vector0, 0, 3);

        double[] vector1 = {1.0, 2.0, 3.0};
        double[] vector2 = {2.0, 3.0, 4.0};
        double[] vector3 = {3.0, 4.0, 5.0};
        double[] vector4 = {1.2, 2.0, 3.0};

        Node node1 = new Node(1L, vector1, 0, 3);
        Node node2 = new Node(2L, vector2, 0, 3);
        Node node3 = new Node(3L, vector3, 0, 3);
        Node node4 = new Node(4L, vector4, 0, 3);


        nodeToAddNeighbour.addNeighbor(0, node1, distanceFunction);
        nodeToAddNeighbour.addNeighbor(0, node2, distanceFunction);
        nodeToAddNeighbour.addNeighbor(0, node3, distanceFunction);
        //when
        nodeToAddNeighbour.addNeighbor(0, node4, distanceFunction);
        //then
        assertEquals(3, nodeToAddNeighbour.getNeighbors(0).size());
        assertTrue(nodeToAddNeighbour.getNeighbors(0).contains(node4));
        assertFalse(nodeToAddNeighbour.getNeighbors(0).contains(node3));
    }

    @Test
    void shouldNotAddTheSameNode() {
        //given
        double[] vector0 = {1.1, 2.0, 3.0};
        Node nodeToAddNeighbour = new Node(1L, vector0, 0, 3);

        double[] vector1 = {1000.0, 2000.0, 3000.0};
        Node node1 = new Node(1L, vector1, 0, 3);
        //when
        nodeToAddNeighbour.addNeighbor(0, node1, distanceFunction);
        nodeToAddNeighbour.addNeighbor(0, node1, distanceFunction);
        nodeToAddNeighbour.addNeighbor(0, node1, distanceFunction);

        //then
        assertEquals(1, nodeToAddNeighbour.getNeighbors(0).size());
        assertTrue(nodeToAddNeighbour.getNeighbors(0).contains(node1));
    }

    @Test
    void shouldAddNeighbourToEmptyList() {
        //given
        double[] vector0 = {1.1, 2.0, 3.0};
        Node nodeToAddNeighbour = new Node(1L, vector0, 0, 3);

        double[] vector1 = {1000.0, 2000.0, 3000.0};
        //when
        Node node1 = new Node(1L, vector1, 0, 3);
        nodeToAddNeighbour.addNeighbor(0, node1, distanceFunction);

        //then
        assertEquals(1, nodeToAddNeighbour.getNeighbors(0).size());
        assertTrue(nodeToAddNeighbour.getNeighbors(0).contains(node1));
    }
}