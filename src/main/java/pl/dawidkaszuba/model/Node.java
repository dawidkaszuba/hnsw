package pl.dawidkaszuba.model;

import lombok.Getter;
import pl.dawidkaszuba.distance.DistanceFunction;

import java.util.*;

@Getter
public class Node {

    private final Long id;
    private final double[] vector;
    private final int level;
    private final Map<Integer, List<Node>> neighborsByLevel;
    private final int maxNeighborsNumber;

    public Node(Long id, double[] vector, int level, int maxNeighborsNumber) {
        this.id = id;
        this.vector = vector;
        this.level = level;
        this.neighborsByLevel = new HashMap<>();
        this.maxNeighborsNumber = maxNeighborsNumber;
    }

    public List<Node> getNeighbors(int level) {
        return Collections.unmodifiableList(neighborsByLevel.getOrDefault(level, new ArrayList<>()));
    }


    public void addNeighbor(int level, Node neighbor, DistanceFunction distanceFunction) {

        List<Node> workingList = new ArrayList<>(getNeighbors(level));

        if (!workingList.contains(neighbor)) {

            if (workingList.size() >= this.maxNeighborsNumber) {
                workingList.add(neighbor);

                workingList.sort((n1, n2) -> {
                    double d1 = distanceFunction.compute(this.vector, n1.vector);
                    double d2 = distanceFunction.compute(this.vector, n2.vector);
                    return Double.compare(d1, d2);
                });

                workingList = workingList.subList(0, this.maxNeighborsNumber);
                neighborsByLevel.put(level, workingList);

            } else {
                neighborsByLevel.computeIfAbsent(level, l -> new ArrayList<>()).add(neighbor);
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(getId(), node.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
