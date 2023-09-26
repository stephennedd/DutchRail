package lists;

import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ReadCsvFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestStationHashMap
{

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testStationHashMap() {
        List<Station> stations = ReadCsvFile.readStations("data/stations.csv");
        StationHashMap testMap = new StationHashMap();

//        for (Station station : stations) {
//            testMap.put(station.getCode(), station);
//        }

        testMap.put(stations.get(2).getCode(),stations.get(2));
        testMap.put(stations.get(3).getCode(),stations.get(3));
        System.out.println("Size: " + testMap.getCount());
        System.out.println("Value: " + testMap.get(stations.get(2).getCode()));
        System.out.println("Value: " + testMap.get(stations.get(3).getCode()));
    }

    @Test
    public void testEmptyHashMap() {

    }
}
