package graphs.pathfinding;

import graphs.TrainStationGraphNode;

public class AStarNode implements Comparable<AStarNode> {
    TrainStationGraphNode node;
    double fScore; // f(n) = g(n) + h(n)
    double hScore; // heuristic score

    AStarNode(TrainStationGraphNode node, double fScore, double hScore) {
        this.node = node;
        this.fScore = fScore;
        this.hScore = hScore;
    }

    @Override
    public int compareTo(AStarNode o) {
        return Double.compare(this.fScore, o.fScore);
    }
}
