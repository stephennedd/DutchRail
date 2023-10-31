package graphs;

import java.util.ArrayList;
import java.util.List;

public class GraphNode <T> {
    T value;
    List<GraphNode> neighbors;

    public GraphNode(T value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    public void addEdge(GraphNode node) {
        neighbors.add(node);
    }
}
