package graphs.pathfinding;

import graphs.TrainStationGraphNode;

public class DijkstraNode implements Comparable<DijkstraNode> {
    public TrainStationGraphNode node;
    int distance;

    DijkstraNode(TrainStationGraphNode node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    @Override
    public int compareTo(DijkstraNode o) {
        return Integer.compare(this.distance, o.distance);
    }
}
