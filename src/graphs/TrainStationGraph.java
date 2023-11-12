package graphs;

import model.Connection;
import model.Station;
import trees.MinHeap;

import java.util.*;
import java.util.regex.Pattern;

import static utils.ReadCsvFile.getStationByCode;

public class TrainStationGraph {
//    public static void main(String[] args) {
//        TrainStationGraph graph = new TrainStationGraph();
//
//        List<Station> stations = utils.ReadCsvFile.readStationsWithValidation("data/stations.csv");
//        for (Station station : stations) {
//            graph.addVertex(station);
//        }
//
//        Pattern regex = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//        List<Connection> connections = utils.ReadCsvFile.readConnectionsWithRegex("data/tracks.csv", regex);
//
//        graph.addAllEdges(connections);
//
//        String result = graph.graphViz();
//        //System.out.println(result);
//
//        // choose two stations
//        Station station1 = getStationByCode("DV");
//        Station station2 = getStationByCode("APD");
//
//        if (station1 == null || station2 == null) {
//            System.out.println("One or both of the stations are null.");
//            return;
//        }
//
//        // create a subgraph with stations within the rectangle
//        TrainStationGraph subgraph = TrainStationGraph.filterStationsInRectangle(graph, station1.getLatitude(), station1.getLongitude(), station2.getLatitude(), station2.getLongitude());
//        String subgraphGraph = subgraph.graphViz();
//
//        //System.out.println(subgraphGraph);
//
//
//        // generate the minimum spanning tree
//        TrainStationGraph mst = TrainStationGraph.generateMinimumSpanningTree(subgraph);
//
//        String mstGraph = mst.graphViz();
//        System.out.println(mstGraph);
//
//    }

    private final Map<String, TrainStationGraphNode> adjacencyList;

    public TrainStationGraph() {
        adjacencyList = new HashMap<>();
    }

    public int size() {
        return adjacencyList.size();
    }

    public void addVertex(Station vertex) {
        if (!adjacencyList.containsKey(vertex.getCode())) {
            adjacencyList.put(vertex.getCode(), new TrainStationGraphNode(vertex));
        }
    }

    public void addEdge(String from, String to, int distance) {
        TrainStationGraphNode fromNode = adjacencyList.get(from);
        TrainStationGraphNode toNode = adjacencyList.get(to);

        if (fromNode != null && toNode != null) {
            fromNode.addEdge(toNode, distance);
        }
    }

    public void addAllEdges(List<Connection> connections) {
        for (Connection connection : connections) {
            addEdge(connection.fromStation, connection.toStation, connection.distanceInKm);
        }
    }

    public boolean containsVertex(Station station) {
        return adjacencyList.containsKey(station.getCode());
    }

    public boolean areConnected(Station station1, Station station2) {
        if (!containsVertex(station1) || !containsVertex(station2)) {
            return false;
        }
        return adjacencyList.get(station1.getCode()).hasNeighbor(adjacencyList.get(station2.getCode()));
    }


    public List<TrainStationGraphNode> getNodes() {
        return new ArrayList<>(adjacencyList.values());
    }

    public static TrainStationGraph filterStationsInRectangle(TrainStationGraph graph, double lat1, double lon1, double lat2, double lon2) {
        // Implement logic to filter stations within the specified rectangle
        TrainStationGraph subgraph = new TrainStationGraph();

        for (TrainStationGraphNode node : graph.getNodes()) {
            Station station = node.getStation();
            if (isWithinRectangle(station.getLatitude(), station.getLongitude(), lat1, lon1, lat2, lon2)) {
                subgraph.addVertex(station);

                // Add all edges to the subgraph
                for (TrainStationGraphNode neighbor : node.getNeighbors()) {
                    if (isWithinRectangle(neighbor.getStation().getLatitude(), neighbor.getStation().getLongitude(), lat1, lon1, lat2, lon2)) {
                        subgraph.addEdge(station.getCode(), neighbor.getStation().getCode(), node.getDistance(neighbor));
                    }
                }
            }
        }

        // Return the subgraph containing stations within the rectangle
        return subgraph;
    }

    public static TrainStationGraph generateMinimumSpanningTree(TrainStationGraph graph) {
        // Implement logic to generate the minimum spanning tree
        TrainStationGraph mst = new TrainStationGraph();

        // Add all nodes to the mst
        for (TrainStationGraphNode node : graph.getNodes()) {
            mst.addVertex(node.getStation());
        }

        // Get all edges
        MinHeap<Edge> edges = getAllEdges(graph);

        // Create a disjoint set
        DisjointSet disjointSet = new DisjointSet(graph.getNodes());

        // Loop until all nodes are connected
        while (!edges.isEmpty()) {
            Edge edge = edges.pop();

            TrainStationGraphNode from = edge.getFrom();
            TrainStationGraphNode to = edge.getTo();

            if (disjointSet.find(from).equals(disjointSet.find(to))) {
                continue;
            }

            disjointSet.union(from, to);
            mst.addEdge(from.getStation().getCode(), to.getStation().getCode(), edge.getWeight());
        }

        return mst;
    }

    public TrainStationGraphNode getNodeByStationCode(String code) {
        return adjacencyList.get(code);
    }

    public String graphViz() {
        StringBuilder dotTree = new StringBuilder();
        dotTree.append("digraph TrainStationGraph {\n");

        for (TrainStationGraphNode node : adjacencyList.values()) {
            dotTree.append("  ").append(node.getStation().getCode()).append(" [label=\"").append(node.getStation().getNameShort()).append("\"]").append("[shape=\"ellipse\" style=\"filled\" fillcolor=\"#1f77b4\"];\n");
            //dotTree.append("  ").append(node.getStation().getCode()).append("\n");

            for (TrainStationGraphNode neighbor : node.getNeighbors()) {
                dotTree.append("  ").append(node.getStation().getCode()).append(" -> ").append(neighbor.getStation().getCode()).append(" [label=\"").append(node.getDistance(neighbor)).append("\"];\n");
            }
        }

        dotTree.append("}\n");
        return dotTree.toString();
    }

    private static boolean isWithinRectangle(double lat, double lon, double lat1, double lon1, double lat2, double lon2) {
        return lat >= Math.min(lat1, lat2) && lat <= Math.max(lat1, lat2)
                && lon >= Math.min(lon1, lon2) && lon <= Math.max(lon1, lon2);
    }

    private static MinHeap<Edge> getAllEdges(TrainStationGraph graph) {
        MinHeap<Edge> minHeap = new MinHeap<>(graph.size());

        for (TrainStationGraphNode node : graph.getNodes()) {
            for (TrainStationGraphNode neighbor : node.getNeighbors()) {
                minHeap.push(new Edge(node, neighbor, node.getDistance(neighbor)));
            }
        }

        return minHeap;
    }

    private static class Edge implements Comparable<Edge> {
        private final TrainStationGraphNode from;
        private final TrainStationGraphNode to;
        private final int weight;

        public Edge(TrainStationGraphNode from, TrainStationGraphNode to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public TrainStationGraphNode getFrom() {
            return from;
        }

        public TrainStationGraphNode getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    static class DisjointSet {
        private final Map<TrainStationGraphNode, TrainStationGraphNode> parent;

        public DisjointSet(List<TrainStationGraphNode> nodes) {
            parent = new HashMap<>();
            for (TrainStationGraphNode node : nodes) {
                parent.put(node, node);
            }
        }

        public TrainStationGraphNode find(TrainStationGraphNode node) {
            if (parent.get(node).equals(node)) {
                return node;
            }
            // Path compression
            parent.put(node, find(parent.get(node)));
            return parent.get(node);
        }

        public void union(TrainStationGraphNode node1, TrainStationGraphNode node2) {
            TrainStationGraphNode root1 = find(node1);
            TrainStationGraphNode root2 = find(node2);
            if (!root1.equals(root2)) {
                parent.put(root1, root2);
            }
        }
    }
}
