package pathfinding;

import model.Connection;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;
import utils.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static model.Station.getStationByCode;
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
        Station startStation = getStationByCode(stations, startCode);
        String endStationCode = "WTV";
        Station endStation = getStationByCode(stations, endStationCode);

        if (startStation != null) {
            Map<Station, Integer> distance = dijkstraAlgorithm.findShortestPath(stations, connections, startCode);
            distance = Sort.sortMapByValue(distance);

//            for (Station station : distance.keySet()) {
//                System.out.println("distance from " + startStation.getCode() + " to " + station.getCode() + ": " + distance.get(station) + " km");
//            };

            int getDistance = distance.get(endStation);
            if (getDistance == Integer.MAX_VALUE) {
                System.out.println("No route found from " + startStation.getNameShort() + " to " + endStation.getNameShort() + ".");
            } else {
                System.out.println("Shortest route from " + startStation.getNameShort() + " to " + endStation.getNameShort() + " is " + getDistance + " km.");
            }

            assertEquals(67, getDistance);
        }

    }

}