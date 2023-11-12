package graphs;
import model.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainStationGraphNode {
    Station station;
    List<TrainStationGraphNode> neighbors;
    Map<TrainStationGraphNode, Integer> distances;

    public TrainStationGraphNode(Station station) {
        this.station = station;
        this.neighbors = new ArrayList<>();
        this.distances = new HashMap<>();
    }

    public void addEdge(TrainStationGraphNode node, int distance) {
        neighbors.add(node);
        distances.put(node, distance);
    }

    public Station getStation() {
        return station;
    }

    public TrainStationGraphNode[] getNeighbors() {
        return neighbors.toArray(new TrainStationGraphNode[0]);
    }

    public int getDistance(TrainStationGraphNode neighbor) {
        return distances.getOrDefault(neighbor, Integer.MAX_VALUE);
    }

    public boolean hasNeighbor(TrainStationGraphNode trainStationGraphNode) {
        return neighbors.contains(trainStationGraphNode);
    }
}
