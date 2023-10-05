package lists;

import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;

import java.io.IOException;
import java.util.List;

public class StationHashMapTest
{

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testGetIndexReturnsCorrectValue() {

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

    @Test
    public void testEmptyHashMap() {

    }
}
