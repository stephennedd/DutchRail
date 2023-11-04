package pathfinding;

import lists.HashTable;
import model.Connection;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {

    DijkstraAlgorithm dijkstraAlgorithm;
    List<Station> stations;
    ArrayList<Connection> connections;

    @BeforeEach
    public void setUp() {
        dijkstraAlgorithm = new DijkstraAlgorithm();
        stations = ReadCsvFile.readStationsWithValidation("data/stations.csv");
        connections = ReadCsvFile.readConnections("data/tracks.csv");
    }

    @Test
    public void testFindShortestPath() {
        String startCode = "HT";
        String endStationCode = "WTV";

        if (startCode != null) {
            ShortestPathResult shortestPath = dijkstraAlgorithm.findShortestPath(stations, connections, startCode, endStationCode);

            if (shortestPath == null) {
                fail("No route found.");
            }

            List<Station> route = shortestPath.getRoute();
            int totalDistance = shortestPath.getTotalDistance();

            assertEquals(16, route.size());
            assertEquals(67, totalDistance);
        }

    }

}