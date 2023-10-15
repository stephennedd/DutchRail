package utils;

import lists.DoublyLinkedList;
import model.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SortTest {
    private DoublyLinkedList<Integer> unsortedList;
    private ArrayList<Connection> connections;
    private ReadCsvFile readCsvFile;

    @BeforeEach
    void setUp() {
        unsortedList = new DoublyLinkedList<>();
        unsortedList.append(5);
        unsortedList.append(3);
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(4);
    }

    @Test
    void insertionSortDoublyLinkedList() {
        DoublyLinkedList<Integer> sortedList = Sort.insertionSortDoublyLinkedList(unsortedList, Integer::compareTo);
        System.out.println(sortedList);
    }

    @Test
    void insertionSortDoublyLinkedListNullList() {
        assertThrows(IllegalArgumentException.class, () -> Sort.insertionSortDoublyLinkedList(null, Integer::compareTo));
    }

    @Test
    void insertionSortDoublyLinkedListEmptyList() {
        DoublyLinkedList<Integer> sortedList = Sort.insertionSortDoublyLinkedList(new DoublyLinkedList<Integer>(), Integer::compareTo);
    }

    @Test
    void insertionSortArraylistConnectionsTimed() {
        readCsvFile = new ReadCsvFile("data/tracks.csv");
        connections = ReadCsvFile.readConnections("data/tracks.csv");
        long startTime = System.nanoTime();
        ArrayList<Connection> sortedConnections = Sort.insertionSortArrayList(connections, Connection::compareByDistance);
        long endTime = System.nanoTime();

        System.out.println("Execution time insertion sort in nanoseconds: " + (endTime - startTime));
    }

    @Test
    void insertionSortArrayListNullList() {
        assertThrows(IllegalArgumentException.class, () -> Sort.insertionSortArrayList(null, Connection::compareByDistance));
    }

    @Test
    void insertionSortArrayListEmptyList() {
        ArrayList<Connection> sortedConnections = Sort.insertionSortArrayList(new ArrayList<Connection>(), Connection::compareByDistance);
    }

    @Test
    void mergeSortArrayListConnectionsTimed() {
        readCsvFile = new ReadCsvFile("data/tracks.csv");
        connections = ReadCsvFile.readConnections("data/tracks.csv");
        long startTime = System.nanoTime();
        ArrayList<Connection> sortedConnections = Sort.mergeSortArrayList(connections, Connection::compareByDistance);
        long endTime = System.nanoTime();
//        for (Connection connection: sortedConnections) {
//            System.out.println(connection);
//        }
        System.out.println("Execution time merge sort in nanoseconds: " + (endTime - startTime));
    }

    @Test
    void mergeSortNullList() {
        assertThrows(IllegalArgumentException.class, () -> Sort.mergeSortArrayList(null, Connection::compareByDistance));
    }
}