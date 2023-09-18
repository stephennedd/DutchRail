package test;

import model.Station;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    @DisplayName("Binary search should throw an assertion error when the station name is null")
    void binarySearchNullStations() {
        assertThrows(AssertionError.class, () -> {
            Station.binarySearchByNameShort(null, "Zaltbommel");
        });
    }

    @Test
    @DisplayName("Binary search should throw an assertion error when the station name is null")
    void binarySearchNullStationsList() throws IOException {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.binarySearchByNameShort(stations, null);
        });

        // assert the error message is correct
        assertEquals("searchName is null", e.getMessage());
    }

    @Test
    @DisplayName("Binary search should throw an assertion error when the list is null")
    void binarySearchNullList() {
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.binarySearchByNameShort(null, "test");
        });

        // assert the error message is correct
        assertEquals("stationNames is null", e.getMessage());
    }

    @Test
    @DisplayName("Binary search should throw an assertion error when the list is empty")
    void binarySearchEmptyList() {
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.binarySearchByNameShort(new ArrayList<>(), "test");
        });

        // assert the error message is correct
        assertEquals("stationNames is empty", e.getMessage());
    }

    @Test
    @DisplayName("Binary search should return -1 when the station is not found")
    void binarySearchNotFound() {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        stations = ReadCsvFile.sortListByNameLong(stations);
        Station station = Station.binarySearchByNameShort(stations, "test");
        assertNull(station);
    }

    @Test
    @DisplayName("Binary search should return the correct index when the station is found")
    void binarySearchFound() throws IOException {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        stations = ReadCsvFile.sortListByNameLong(stations);
        Station station = Station.binarySearchByNameShort(stations, "Zaltbommel");
        assertNotNull(station);
    }

    @Test
    @DisplayName("Binary search on unsorted list should throw an assertion error")
    void binarySearchUnsortedList() throws IOException {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        // reverse the list
        List<Station> reversedStations = new ArrayList<>();
        for (int i = stations.size() - 1; i >= 0; i--) {
            reversedStations.add(stations.get(i));
        }
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.binarySearchByNameShort(stations, "Zaltbommel");
        });

        // assert the error message is correct
        assertEquals("stationNames is not sorted alphabetically", e.getMessage());
    }

    @Test
    @DisplayName("Linear search should throw an assertion error when the station name is null")
    void linearSearchNullStationsList() throws IOException {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.linearSearchByNameShort(stations, null);
        });

        // assert the error message is correct
        assertEquals("searchedStation is null", e.getMessage());
    }

    @Test
    @DisplayName("Linear search should throw an assertion error when the list is null")
    void linearSearchNullList() {
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.linearSearchByNameShort(null, "test");
        });

        // assert the error message is correct
        assertEquals("stations is null", e.getMessage());
    }

    @Test
    @DisplayName("Linear search should throw an assertion error when the list is empty")
    void linearSearchEmptyList() {
        AssertionError e = assertThrows(AssertionError.class, () -> {
            Station.linearSearchByNameShort(new ArrayList<>(), "test");
        });

        // assert the error message is correct
        assertEquals("stations is empty", e.getMessage());
    }

    @Test
    @DisplayName("Linear search should return -1 when the station is not found")
    void linearSearchNotFound() throws IOException {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        Station station = Station.linearSearchByNameShort(stations, "thisstationdoesnotexist");
        assertNull(station);
    }

    @Test
    @DisplayName("Linear search should return the correct index when the station is found")
    void linearSearchFound() throws IOException {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        Station station = Station.linearSearchByNameShort(stations, "Zaltbommel");
        assertNotNull(station);
    }

    @Test
    @DisplayName("Read stations from file should not throw an assertion error")
    void readStations() {
        assertDoesNotThrow(() -> {
            ReadCsvFile.readStations("data/stations.csv");
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

    @Test
    @DisplayName("Get station names should not throw an assertion error")
    void getStationNames() {
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station("test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test"));
        assertDoesNotThrow(() -> {
            Station.getStationNames(stations);
        });
    }
}