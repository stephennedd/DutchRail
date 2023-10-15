package assignments;

import model.Connection;
import utils.ReadCsvFile;
import utils.Sort;

import java.util.ArrayList;
// should be excluded from unit tests coverage
public class Week3 {

    private static final String CONNECTIONS_FILE = "data/tracks.csv";

    public static void main(String[] args) {
        System.out.println("Week 3");
        ReadCsvFile readCsvFile = new ReadCsvFile(CONNECTIONS_FILE);
        System.out.println("\nInsertion sort by distance");
        ArrayList<Connection> connections = ReadCsvFile.readConnections(CONNECTIONS_FILE);

        long startTime = System.nanoTime();
        Sort.insertionSortArrayList(connections, Connection::compareByDistance);
        long endTime = System.nanoTime();

        System.out.println("Execution time insertion sort in nanoseconds: " + (endTime - startTime) + "ns");
        System.out.println("Execution time insertion sort in milliseconds: " + (endTime - startTime) / 1000000 + "ms");
        System.out.println("______________________________________________________");

        System.out.println("\nMerge sort by distance");
        ArrayList<Connection> unsortedConnections = ReadCsvFile.readConnections(CONNECTIONS_FILE);
        startTime = System.nanoTime();
        Sort.mergeSortArrayList(unsortedConnections, Connection::compareByDistance);
        endTime = System.nanoTime();
        System.out.println("Execution time merge sort in nanoseconds: " + (endTime - startTime) + "ns");
        System.out.println("Execution time merge sort in milliseconds: " + (endTime - startTime) / 1000000 + "ms");
    }
}
