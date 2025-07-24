package pl.dawidkaszuba.model;

import java.util.*;

public class Node {
    private final int id;
    private final double[] vector;
    private final int level;
    private final Map<Integer, List<Node>> neighborsByLevel;

    public Node(int id, double[] vector, int level) {
        this.id = id;
        this.vector = vector;
        this.level = level;
        this.neighborsByLevel = new HashMap<>();
    }

    public List<Node> getNeighbors(int level) {
        return Collections.unmodifiableList(neighborsByLevel.getOrDefault(level, new ArrayList<>()));
    }


    public void addNeighbor(int level, Node neighbor) {
        neighborsByLevel.computeIfAbsent(level, l -> new ArrayList<>()).add(neighbor);
    }
}
