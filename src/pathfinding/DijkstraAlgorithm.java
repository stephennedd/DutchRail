package pathfinding;

import lists.HashTable;
import model.Connection;
import model.Station;
import trees.MinHeap;
import java.util.*;

import static model.Station.getStationByCode;

public class DijkstraAlgorithm {
    public ShortestPathResult findShortestPath(List<Station> stations, List<Connection> connections, String startPoint, String endPoint) {
        MinHeap<Station> minHeap = new MinHeap<>(stations.size()); // Create a min heap with the size of the stations list
        HashTable<Station, Integer> distance = new HashTable<>(); // Create a hash table to store the distances
        HashTable<Station, Station> previousStation = new HashTable<>(); // Create a hash table to store the previous station

        Station start = getStationByCode(stations, startPoint); // Get the start station

        for (Station station : stations) {
            distance.put(station, Integer.MAX_VALUE); // Set all distances to infinity when starting, in the dijkstra algorithm this means its unreachable
        }
        distance.put(start, 0); // Set the distance to the start station to 0

        minHeap.push(start); // Add the start station to the min heap

        while (!minHeap.isEmpty()) { // Loop until the min heap is empty
            Station currentStation = minHeap.pop(); // Get the station with the shortest distance
            int currentDistance = distance.get(currentStation); // Get the distance to the current station

            for (Connection connection : connections) { // Loop through all connections
                if (connection.fromStation.equals(currentStation.getCode())) { // Check if the connection is from the current station
                    Station neighbor = getStationByCode(stations, connection.toStation); // Get the neighbor station
                    int altDistance = currentDistance + connection.distanceInKm; // Use the distance from the connection

                    if (neighbor != null) { // Check if the neighbor station exists
                        if (altDistance < distance.get(neighbor)) { // Check if the new distance is shorter than the current distance
                            distance.put(neighbor, altDistance); // Update the distance
                            previousStation.put(neighbor, currentStation); // Update the previous station
                            minHeap.push(neighbor); // Add the neighbor to the min heap
                        }
                    }
                }
            }
        }
        // check if there is a path
        if (distance.get(getStationByCode(stations, endPoint)) == Integer.MAX_VALUE) {
            return null;
        }

        // Calculate the total distance
        int totalDistance = 0;
        Station currentStation = getStationByCode(stations, endPoint);

        while (currentStation != null) {
            Station previous = previousStation.get(currentStation);

            if (previous != null) {
                totalDistance += distance.get(currentStation) - distance.get(previous);
            }

            currentStation = previous;
        }

        // Create the route
        List<Station> route = new ArrayList<>();
        currentStation = getStationByCode(stations, endPoint);

        while (currentStation != null) {
            route.add(currentStation);
            currentStation = previousStation.get(currentStation);
        }

        Collections.reverse(route);

        // Create and return the result instance
        return new ShortestPathResult(route, totalDistance);
    }


//    public static void main(String[] args) {
//        // create test stations and connections
//        Station station1 = new Station("A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A");
//        Station station2 = new Station("B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B");
//        Station station3 = new Station("C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C");
//
//        Connection connection1 = new Connection("A", "B", 1, 1, 1);
//        Connection connection2 = new Connection("A", "C", 2, 1, 1);
//        Connection connection3 = new Connection("B", "C", 3, 1, 1);
//        Connection connection4 = new Connection("B", "A", 1, 1, 1);
//        Connection connection5 = new Connection("C", "A", 2, 1, 1);
//        Connection connection6 = new Connection("C", "B", 3, 1, 1);
//        Connection connection7 = new Connection("A", "E", 3, 1, 1);
//
//
////        List<Station> stations = List.of(station1, station2, station3);
////        List<Connection> connections = List.of(connection1, connection2, connection3, connection4, connection5, connection6, connection7);
//
////        // Test the algorithm
//        List<Station> stations = utils.ReadCsvFile.readStationsWithValidation("data/stations.csv");
//        List<Connection> connections = utils.ReadCsvFile.readConnections("data/tracks.csv");
//
//        Station startStation = stations.get(1);
//        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
//
//        // get the distance map
//        long startTime = System.nanoTime();
//        Map<Station, Integer> distance = dijkstraAlgorithm.findShortestPath(stations, connections, startStation.getCode());
//        distance = sortMapByValue(distance); // sort the map by value
//        long endTime = System.nanoTime();
//        long duration = (endTime - startTime) / 1000000; // convert to milliseconds
//
//        System.out.println("duration: " + duration + " ms");
////        for (Station station : distance.keySet()) {
////            System.out.println("distance from " + startStation.getNameShort() + " to " + station.getNameShort() + ": " + distance.get(station) + " km");
////        }
//    }
}
