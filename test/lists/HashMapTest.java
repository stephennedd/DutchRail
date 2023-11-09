package lists;

import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest
{
    private StationHashMap stationMap;
    private HashTable<Integer,Integer> hashTable;
    private Station station1;
    private Station station2;
    private Station station3;
    private Station station4;
    private Station station5;
    private Station station6;
    private Station station7;
    private Station station8;
    private Station station9;
    private Station station10;
    private Station station11;
    @BeforeEach
    public void setUp() {
        stationMap = new StationHashMap();
        station1 = new Station("227", "Aa", "8400388", "'t Harde", "'t Harde", "'t Harde", "t-harde", "NL", "stoptreinstation", 52.4091682, 5.893611);
        station2 = new Station("8", "BB", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station3 = new Station("1231", "bB", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station4 = new Station("4235", "bb", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station5 = new Station("23542", "EEE", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station6 = new Station("412334", "FFF", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station7 = new Station("2361", "GGG", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station8 = new Station("14673", "HHH", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station9 = new Station("34714", "III", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station10 = new Station("5825", "JJJ", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);
        station11 = new Station("412334", "KKK", "8015345", "Aachen", "Aachen Hbf", "Aachen Hbf", "aachen-hbf", "D", "knooppuntIntercitystation", 50.7678, 6.091499);

        hashTable = new HashTable<>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stationMap.isEmpty());
        stationMap.put(station1);
        assertFalse(stationMap.isEmpty());
    }

    @Test
    public void testGetSize() {
        assertEquals(0, stationMap.size());
        stationMap.put(station1);
        stationMap.put(station2);
        assertEquals(2, stationMap.size());
    }

    @Test
    public void testContainsKey() {
        assertFalse(stationMap.containsKey("Aa"));
        stationMap.put(station1);
        assertTrue(stationMap.containsKey("Aa"));
    }

    @Test
    public void testPut() {
        stationMap.put(station1);
        assertTrue(stationMap.containsKey("Aa"));
        assertEquals(station1, stationMap.get("Aa"));

        // Test updating the value for an existing key
        Station updatedStation = new Station("227", "HDE", "8400388", "'t Harde", "'t Harde", "'t Harde", "t-harde", "NL", "stoptreinstation", 52.4091682, 5.893611);
        stationMap.put(updatedStation);
        assertEquals(updatedStation, stationMap.get("HDE"));
    }

    @Test
    public void testPutNullKey() {
        assertThrows(AssertionError.class, () -> stationMap.put(null));
    }

    @Test
    public void testPutSameValues() {
        stationMap.put(station1);
        stationMap.put(station1);
        assertEquals(1, stationMap.size());
    }

    @Test
    public void testGet() {
        assertNull(stationMap.get("Aa"));
        stationMap.put(station1);
        assertEquals(station1, stationMap.get("Aa"));
    }

    @Test
    public void testGetNullKey() {
        assertThrows(AssertionError.class, () -> stationMap.get(null));
    }

    @Test
    public void testRemoveWithSingleElement() {
        stationMap.put(station1);
        assertEquals(true, stationMap.remove("Aa"));
    }

    @Test
    public void testRemoveWithMultipleElements() {
        // Add multiple elements to the same bucket
        stationMap.put(station1);
        stationMap.put(station2);
        stationMap.put(station3);
        stationMap.put(station4);
        stationMap.put(station5);
        stationMap.put(station6);
        stationMap.put(station7);
        stationMap.put(station8);
        stationMap.put(station9);
        stationMap.put(station10);
        stationMap.put(station11);

        assertTrue(stationMap.remove("BB"));
        assertFalse(stationMap.containsKey("BB"));
        assertEquals(10, stationMap.size());
    }

    @Test
    public void testRemoveFromEmptyBucket() {
        StationHashMap hashTable = new StationHashMap();
        assertFalse(hashTable.remove("key"));
    }

    @Test
    public void testRemoveNullKey() {
        assertThrows(AssertionError.class, () -> stationMap.remove(null));
    }

    @Test
    public void testRemap() {
        StationHashMap emptyMap = new StationHashMap();

        // Add enough entries to trigger a remap
        for (int i = 0; i < 20; i++) {
            emptyMap.put(new Station("Key" + i, "Code" + i, "ShortCode" + i, "NameShort" + i, "NameMedium" + i, "NameLong" + i, "Slug" + i, "CountryCode" + i, "Type" + i, 0.0, 0.0 ));
        }


        // Check that all entries are still present after remap
        for (int i = 0; i < 20; i++) {
            assertTrue(emptyMap.containsKey("Code" + i));
            assertEquals("NameShort" + i, emptyMap.get("Code" + i).getNameShort());
        }
    }

    @Test
    void testContainsKeyNullKey() {
        assertThrows(AssertionError.class, () -> stationMap.containsKey(null));
    }

    @Test
    void testStationHashMap() throws IOException {
        List<Station> stations = ReadCsvFile.readStationsWithValidation("data/stations.csv");
        StationHashMap testMap = new StationHashMap();

        for (Station station : stations) {
            testMap.put(station);
        }

        assert testMap.size() == 578;
        System.out.println("Value: " + testMap.get("HT"));
    }

    @Test
    void testGetIndex() {
        System.out.println(stationMap.getIndex("Aa"));
        System.out.println(stationMap.getIndex("BB"));
        System.out.println(stationMap.getIndex("bB"));
        System.out.println(stationMap.getIndex("bb"));
    }

    @Test
    void testHashTableIsEmpty() {
        assert hashTable.isEmpty();
        assertEquals(0, hashTable.size());
    }

    @Test
    void testHashTableRemoveInt() {
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);

        assertEquals(3, hashTable.size());
        assertTrue(hashTable.remove(2));
        assertEquals(2, hashTable.size());
        assertFalse(hashTable.remove(2));
    }

    @Test
    void testHashTableContainsKey() {
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);

        assertTrue(hashTable.containsKey(1));
        assertTrue(hashTable.containsKey(2));
        assertTrue(hashTable.containsKey(3));
        assertFalse(hashTable.containsKey(4));
    }

    @Test
    void testHashMapAreEquals() {

    }

}
