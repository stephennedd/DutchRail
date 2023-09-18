import model.Station;
import utils.ReadCsvFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Path to the stations.csv file
        String filepath = "data/stations.csv";

        // Read the stations from the file
        List<Station> stations = ReadCsvFile.readStations(filepath);
        System.out.println(stations.size());

        // add station names to an ArrayList
        List<String> stationNames = Station.getStationNames(stations);
        for (String stationName: stationNames) {
            System.out.println(stationName);
        }

    }
}