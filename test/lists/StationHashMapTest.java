package lists;

import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StationHashMapTest
{

    private StationHashMap stationMap;
    private Station station1;
    private Station station2;

    @BeforeEach
    public void setUp() {
        stationMap = new StationHashMap();
        station1 = new Station("227", "HDE", "8400388", "'t Harde", "'t Harde", "'t Harde", "t-harde", "NL", "stoptreinstation", "52.4091682", "5.893611");
        station2 = new Station("8", "AHBF", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", "50.7678", "6.091499");
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stationMap.isEmpty());
        stationMap.put(station1);
        assertFalse(stationMap.isEmpty());
    }

    @Test
    public void testGetCount() {
        assertEquals(0, stationMap.getCount());
        stationMap.put(station1);
        stationMap.put(station2);
        assertEquals(2, stationMap.getCount());
    }

    @Test
    public void testContainsKey() {
        assertFalse(stationMap.containsKey("HDE"));
        stationMap.put(station1);
        assertTrue(stationMap.containsKey("HDE"));
    }

    @Test
    public void testPut() {
        stationMap.put(station1);
        assertTrue(stationMap.containsKey("HDE"));
        assertEquals(station1, stationMap.get("HDE").getValue());

        // Test updating the value for an existing key
        Station updatedStation = new Station("227", "HDE", "8400388", "'t Harde", "'t Harde", "'t Harde", "t-harde", "NL", "stoptreinstation", "52.4091682", "5.893611");
        stationMap.put(updatedStation);
        assertEquals(updatedStation, stationMap.get("HDE").getValue());
    }

    @Test
    public void testGet() {
        assertNull(stationMap.get("HDE"));
        stationMap.put(station1);
        assertEquals(station1, stationMap.get("HDE").getValue());
    }

    @Test
    public void testRemove() {
        assertNull(stationMap.remove("HDE"));
        stationMap.put(station1);
        assertEquals(station1, stationMap.remove("HDE"));
        assertTrue(stationMap.get("HDE").isDeleted());
    }

    @Test
    public void testRemap() {
        // Add enough entries to trigger a remap
        for (int i = 0; i < 17; i++) {
            stationMap.put(new Station("Key" + i, "Value" + i, "ShortCode" + i, "CountryCode" + i, "Country" + i, "Name" + i, "NameShort" + i, "Type" + i, "Latitude" + i, "Longitude" + i, "Synonyms" + i));
        }

        // Check that all entries are still present after remap
        for (int i = 0; i < 17; i++) {
            assertTrue(stationMap.containsKey("Key" + i));
            assertEquals("Value" + i, stationMap.get("Key" + i).getValue().getNameShort());
        }
    }

    @Test
    public void testStationHashMap() throws IOException {
        List<Station> stations = ReadCsvFile.readStationsWithValidation("data/stations.csv");
        StationHashMap testMap = new StationHashMap();

        for (Station station : stations) {
            testMap.put(station);
        }

        assert testMap.getCount() == 578;
        System.out.println("Value: " + testMap.get("HT"));
    }

}
