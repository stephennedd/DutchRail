package test;

import model.Station;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    void binarySearchNullStations() {
        assertThrows(AssertionError.class, () -> {
            Station.binarySearch(null, "Zaltbommel");
        });
    }

    @Test
    @DisplayName("Binary search should throw an assertion error when the station name is null")
    void binarySearchNullStationsList() throws IOException {
        ArrayList<Station> stations = Station.readStations("data/stations.csv");
        ArrayList<String> names = Station.getStationNames(stations);
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.binarySearch(names, null);
        });

        // assert the error message is correct
        assertEquals("searchName is null", e.getMessage());
    }

    @Test
    @DisplayName("Binary search should throw an assertion error when the list is null")
    void binarySearchNullList() {
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.binarySearch(null, "test");
        });

        // assert the error message is correct
        assertEquals("stationNames is null", e.getMessage());
    }

    @Test
    @DisplayName("Binary search should throw an assertion error when the list is empty")
    void binarySearchEmptyList() {
        assertThrows(AssertionError.class, () -> {
            Station.binarySearch(new ArrayList<String>(), "test");
        });
    }

    @Test
    @DisplayName("Binary search should return -1 when the station is not found")
    void binarySearchNotFound() throws IOException {
        ArrayList<Station> stations = Station.readStations("data/stations.csv");
        ArrayList<String> names = Station.getStationNames(stations);
        int index = Station.binarySearch(names, "test");
        assertEquals(-1, index);
    }

    @Test
    @DisplayName("Binary search should return the correct index when the station is found")
    void binarySearchFound() throws IOException {
        ArrayList<Station> stations = Station.readStations("data/stations.csv");
        ArrayList<String> names = Station.getStationNames(stations);
        int index = Station.binarySearch(names, "Zaltbommel");
        assertEquals(563, index);
    }

    @Test
    @DisplayName("Read stations from file should not throw an assertion error")
    void readStations() {
        assertDoesNotThrow(() -> {
            Station.readStations("data/stations.csv");
        });
    }

    @Test
    @DisplayName("The to string method should return the correct string")
    void testToString() {
        Station station = new Station("test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
        String expected = "Station{" +
                "id='" + "test" + '\'' +
                ", code='" + "test" + '\'' +
                ", shortCode='" + "test" + '\'' +
                ", nameShort='" + "test" + '\'' +
                ", nameMedium='" + "test" + '\'' +
                ", nameLong='" + "test" + '\'' +
                ", slug='" + "test" + '\'' +
                ", countryCode='" + "test" + '\'' +
                ", type='" + "test" + '\'' +
                ", latitude='" + "test" + '\'' +
                ", longitude='" + "test" + '\'' +
                '}';
        assertEquals(expected, station.toString());
    }
}