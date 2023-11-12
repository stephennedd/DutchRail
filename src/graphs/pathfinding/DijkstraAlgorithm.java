package graphs.pathfinding;

import graphs.TrainStationGraph;
import graphs.TrainStationGraphNode;
import model.Connection;
import model.Station;
import trees.MinHeap;
import utils.ReadCsvFile;
import java.util.*;

public class DijkstraAlgorithm {
//    public static void main(String[] args) {
//        List<Station> stations = ReadCsvFile.readStationsWithValidation("data/stations.csv");
//        ArrayList<Connection> connections = ReadCsvFile.readConnections("data/tracks.csv");
//
//        // create graph
//        TrainStationGraph graph = new TrainStationGraph();
//
//        long startTime = System.nanoTime();
//        // add vertices
//        for (Station station: stations) {
//            graph.addVertex(station);
//        }
//        long endTime = System.nanoTime();
//
//        String startStation = "DV";
//        String endStation = "APD";
//
//        // add edges
//        System.out.println("Time taken to add vertices: \t" + (endTime - startTime) + " nanoseconds");
//        startTime = System.nanoTime();
//        graph.addAllEdges(connections);
//        endTime = System.nanoTime();
//        System.out.println("Time taken to add edges: \t" + (endTime - startTime) + " nanoseconds");
//
//
//        // find the shortest path
//        TrainStationGraphNode startNode = graph.getNodeByStationCode(startStation);
//
//        if (startNode != null) {
//            startTime = System.nanoTime();
//            Map<Station, Integer> shortestDistances = dijkstra(graph, startNode);
//            endTime = System.nanoTime();
//            System.out.println("Time taken to run dijkstra algorithm: \t" + (endTime - startTime) + " nanoseconds");
//
//            // print shortest distance from start to end station
//            for (Map.Entry<Station, Integer> entry : shortestDistances.entrySet()) {
//                if (entry.getKey().getCode().equals(endStation)) {
//                    System.out.println("\tShortest distance from " + startStation + " to " + endStation + " is " + entry.getValue() + " km.");
//                }
//            }
//
//            // reconstruct the path
//
//
//        } else {
//            System.out.println("Start station not found.");
//        }
//    }

    public static Map<Station, Integer> dijkstra(TrainStationGraph graph, TrainStationGraphNode startNode) {
        Map<Station, Integer> shortestDistances = new HashMap<>();
        MinHeap<DijkstraNode> minHeap = new MinHeap<DijkstraNode>(graph.size());

        for (TrainStationGraphNode node : graph.getNodes()) {
            shortestDistances.put(node.getStation(), Integer.MAX_VALUE);
        }
        shortestDistances.put(startNode.getStation(), 0);

        minHeap.push(new DijkstraNode(startNode, 0));

        while (!minHeap.isEmpty()) {
            DijkstraNode currentNode = minHeap.pop();

            // update distances for all neighbors
            for (TrainStationGraphNode neighbor : currentNode.node.getNeighbors()) {
                int newDistance = shortestDistances.get(currentNode.node.getStation()) + currentNode.node.getDistance(neighbor);

                if (newDistance < shortestDistances.get(neighbor.getStation())) {
                    shortestDistances.put(neighbor.getStation(), newDistance);
                    minHeap.push(new DijkstraNode(neighbor, newDistance));
                }
            }
        }

        return shortestDistances;
    }
}
