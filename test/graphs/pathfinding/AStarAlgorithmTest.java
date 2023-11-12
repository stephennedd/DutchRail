package graphs.pathfinding;

import graphs.TrainStationGraph;
import graphs.TrainStationGraphNode;
import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class AStarAlgorithmTest {

    SinglyLinkedList<Station> stations;
    ArrayList<Connection> connections;

    TrainStationGraph graph = new TrainStationGraph();

    @BeforeEach
    void setUp() {
        Pattern regex = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        stations = ReadCsvFile.readStationsWithRegex("data/stations.csv", regex);
        connections = ReadCsvFile.readConnections("data/tracks.csv");

        // add vertices and edges
        for (Station station: stations) {
            graph.addVertex(station);
        }

        graph.addAllEdges(connections);
    }

    @Test
    void testAStar() {
        Station endStation = stations.get(45); // APD

        TrainStationGraphNode startNode = graph.getNodeByStationCode("DV");
        TrainStationGraphNode endNode = graph.getNodeByStationCode("APD");

        SinglyLinkedList<Station> shortestPath = AStarAlgorithm.aStar(graph, startNode, endNode);

        for (Station station : shortestPath) {
            if (station.getCode().equals(endStation.getCode())) {
                assertEquals("31, APD, 8400066, Apeldoorn, Apeldoorn, Apeldoorn, apeldoorn, NL, knooppuntIntercitystation, 52.209167480469, 5.9702777862549 -> 33, APDO, 8400229, Osseveld, Osseveld, Apeldoorn Osseveld, apeldoorn-osseveld, NL, stoptreinstation, 52.215278625488, 6.0044445991516 -> 460, TWL, 8400599, Twello, Twello, Twello, twello, NL, stoptreinstation, 52.237777709961, 6.0986108779907 -> 153, DV, 8400173, Deventer, Deventer, Deventer, deventer, NL, knooppuntIntercitystation, 52.257499694824, 6.1605553627014 -> null", shortestPath.toString());
            }
        }
    }

    @Test
    void testNoPathFound() {
        TrainStationGraphNode startNode = graph.getNodeByStationCode("HTO");
        TrainStationGraphNode endNode = graph.getNodeByStationCode("AHBF");

        SinglyLinkedList<Station> shortestPath = AStarAlgorithm.aStar(graph, endNode, startNode);

        assertNull(shortestPath);
    }
}