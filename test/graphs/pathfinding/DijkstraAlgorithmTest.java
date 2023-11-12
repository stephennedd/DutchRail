package graphs.pathfinding;

import graphs.TrainStationGraph;
import graphs.TrainStationGraphNode;
import graphs.pathfinding.DijkstraAlgorithm;
import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static utils.ReadCsvFile.getStationByCode;

class DijkstraAlgorithmTest {

    SinglyLinkedList<Station> stations;
    ArrayList<Connection> connections;

    TrainStationGraph graph = new TrainStationGraph();

    @BeforeEach
    public void setUp() {
        Pattern pattern = Pattern.compile(",");
        stations = ReadCsvFile.readStationsWithRegex("data/stations.csv", pattern);
        connections = ReadCsvFile.readConnections("data/tracks.csv");

        // add vertices and edges
        for (Station station: stations) {
            graph.addVertex(station);
        }

        graph.addAllEdges(connections);
    }

    @Test
    public void testFindShortestPath() {
        Station endStation = stations.get(45); // APD

        TrainStationGraphNode startNode = graph.getNodeByStationCode("DV");

        Map<Station, Integer> shortestPath = DijkstraAlgorithm.dijkstra(graph, startNode);

        for (Map.Entry<Station, Integer> entry : shortestPath.entrySet()) {
            if (entry.getKey().getCode().equals(endStation.getCode())) {
                assertEquals(15, entry.getValue());
            }
        }

    }

}