package graphs.pathfinding;

import graphs.TrainStationGraph;
import graphs.TrainStationGraphNode;
import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import trees.MinHeap;
import utils.ReadCsvFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AStarAlgorithm {
//    public static void main(String[] args) {
//        List<Station> stations = ReadCsvFile.readStationsWithValidation("data/stations.csv");
//        ArrayList<Connection> connections = ReadCsvFile.readConnections("data/tracks.csv");
//
//        // create graph
//        TrainStationGraph graph = new TrainStationGraph();
//
//        for (Station station: stations) {
//            graph.addVertex(station);
//        }
//
//        // add edges
//        graph.addAllEdges(connections);
//
//        TrainStationGraphNode startNode = graph.getNodeByStationCode("DV");
//        TrainStationGraphNode endNode = graph.getNodeByStationCode("APD");
//
//        if (startNode != null && endNode != null) {
//            SinglyLinkedList<Station> shortestPath = aStar(graph, startNode, endNode);
//
//            if (shortestPath != null) {
//                System.out.println("Shortest path from " + startNode.getStation().getNameShort() + " to " + endNode.getStation().getNameShort() + ":");
//                for (Station station : shortestPath) {
//                    System.out.println("\t->" + station.getNameShort());
//                }
//            } else {
//                System.out.println("No path found.");
//            }
//        } else {
//            System.out.println("Start or end station not found in the graph.");
//        }
//    }

    public static SinglyLinkedList<Station> aStar(TrainStationGraph graph, TrainStationGraphNode startNode, TrainStationGraphNode endNode) {
        MinHeap<AStarNode> minHeap = new MinHeap<>(graph.size());
        Map<TrainStationGraphNode, Integer> gScores = new java.util.HashMap<>();
        Map<TrainStationGraphNode, TrainStationGraphNode> cameFrom = new java.util.HashMap<>();

        gScores.put(startNode, 0);
        double hScore = heuristicCostEstimate(startNode, endNode);
        minHeap.push(new AStarNode(startNode, hScore, hScore));

        while (!minHeap.isEmpty()) {
            AStarNode current = minHeap.pop();
            TrainStationGraphNode currentNode = current.node;

            if (currentNode.equals(endNode)) {
                return reconstructPath(cameFrom, endNode);
            }

            for (TrainStationGraphNode neighbor: currentNode.getNeighbors()) {
                int tentativeGScore = gScores.get(currentNode) + currentNode.getDistance(neighbor);

                if (tentativeGScore < gScores.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, currentNode);
                    gScores.put(neighbor, tentativeGScore);
                    int hScoreNeighbor = heuristicCostEstimate(neighbor, endNode);
                    int fScoreNeighbor = tentativeGScore + hScoreNeighbor;
                    minHeap.push(new AStarNode(neighbor, fScoreNeighbor, hScoreNeighbor));
                }
            }
        }

        return null; // no path found
    }

    private static int heuristicCostEstimate(TrainStationGraphNode startNode, TrainStationGraphNode endNode) {
        return startNode.getDistance(endNode);
    }

    private static SinglyLinkedList<Station> reconstructPath(Map<TrainStationGraphNode, TrainStationGraphNode> cameFrom, TrainStationGraphNode goalNode) {
        SinglyLinkedList<Station> path = new SinglyLinkedList<>();
        TrainStationGraphNode current = goalNode;

        while (current != null) {
            path.append(current.getStation());
            current = cameFrom.get(current);
        }

        path.reverseList();
        return path;
    }
}
