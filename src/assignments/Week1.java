package assignments;

import model.Station;
import utils.ReadCsvFile;
import java.util.List;

public class Week1 {
    public static void main(String[] args) {

        List<Station> data = ReadCsvFile.readStations("data/stations.csv");
        String firstStationInList = "Aachen";
        String lastStationInList = "Stadshagen";
        String randomStationInList = "Zaltbommel";
        Station foundStation;

        // LINEAR SEARCH (first station in the list and last station in the list)

        foundStation = Station.linearSearchByNameShort(data, firstStationInList);
        System.out.println();
        foundStation = Station.linearSearchByNameShort(data, lastStationInList);
        System.out.println();
        foundStation = Station.linearSearchByNameShort(data, randomStationInList);
        System.out.println();

        // BINARY SEARCH (first station in the list and last station in the list)
        foundStation = Station.binarySearchByNameShort(data, firstStationInList);
        System.out.println();
        foundStation = Station.binarySearchByNameShort(data, lastStationInList);
        System.out.println();
        foundStation = Station.binarySearchByNameShort(data, randomStationInList);

    }
}
