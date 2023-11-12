package utils;

import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ReadCsvFileTest {

    List<Station> stationList;
    ArrayList<Connection> connections;

    ReadCsvFile readCsvFile;

    @BeforeEach
    void setUp() {
        readCsvFile = new ReadCsvFile("data/stations.csv");
    }

    @Test
    void readTestFileNotFound() {
        ReadCsvFile testReadCsvFile = new ReadCsvFile("data/sdasd.csv");
        assertThrows(RuntimeException.class, () -> testReadCsvFile.read());
    }

    @Test
    void readConnections() {
        connections = ReadCsvFile.readConnections("data/tracks.csv");
        //System.out.println(connections);
    }

    @Test
    void readStations() {
        stationList = ReadCsvFile.readStations("data/stations.csv");
        //System.out.println(stationList);
    }

    @Test
    void readConnectionsWithRegex() {
        Pattern pattern = Pattern.compile(",");
        connections = ReadCsvFile.readConnectionsWithRegex("data/tracks.csv", pattern);
        //System.out.println(connections);
    }

    @Test
    void getStationByCode() {
        stationList = ReadCsvFile.readStations("data/stations.csv");
        Station station = ReadCsvFile.getStationByCode("ST");
        assertNotNull(station);
    }

    @Test
    void getStationByCodeNotFound() {
        stationList = ReadCsvFile.readStations("data/stations.csv");
        Station station = ReadCsvFile.getStationByCode("SdasdaT");
        assertNull(station);
    }
}
