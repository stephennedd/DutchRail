import model.Station;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // Path to the stations.csv file
        String filepath = "data/stations.csv";

        // Read the stations from the file
        ArrayList<Station> stations = Station.readStations(filepath);
        System.out.println(stations.size());

        // add station names to an ArrayList
        ArrayList<String> stationNames = Station.getStationNames(stations);
        System.out.println(stationNames);

        // search for a station by name using binary search

        // start time in milliseconds
        long startTime = System.nanoTime();
        String searchedStation = "Zaltbommel";
        int station = Station.binarySearch(stationNames, searchedStation);
        // Check the result
        if (station != -1) {
            System.out.println("Found " + searchedStation + " at index " + station);
        } else {
            System.out.println(searchedStation + " not found in the list.");
        }
        // end time
        long endTime = System.nanoTime();
        // calculate time difference
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

    }
}