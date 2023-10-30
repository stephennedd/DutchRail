package utils;

import model.Connection;
import model.Station;

import java.util.*;

public class AStarAlgorithm {
    public List<Station> findShortestPath(List<Station> stations, List<Connection> connections, String startCode, String goalCode) {
        // Convert station codes to Station objects
        Station startStation = getStationByCode(stations, startCode);
        Station goalStation = getStationByCode(stations, goalCode);

        Map<Station, Station> cameFrom = new HashMap<>();
        Map<Station, Integer> gScore = new HashMap<>();
        Map<Station, Integer> fScore = new HashMap<>();
        PriorityQueue<Station> openSet = new PriorityQueue<>(Comparator.comparingInt(fScore::get));

        gScore.put(startStation, 0);
        fScore.put(startStation, heuristicEstimate(startStation, goalStation));
        openSet.add(startStation);

        while (!openSet.isEmpty()) {
            Station current = openSet.poll();

            if (current == goalStation) {
                return reconstructPath(cameFrom, current);
            }

            for (Connection connection : connections) {
                if (connection.fromStation.equals(current.getCode())) {
                    Station neighbor = getStationByCode(stations, connection.toStation);
                    int tentativeGScore = gScore.get(current) + 1; // Count the connection as cost 1

                    if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        cameFrom.put(neighbor, current);
                        gScore.put(neighbor, tentativeGScore);
                        fScore.put(neighbor, tentativeGScore + heuristicEstimate(neighbor, goalStation));

                        if (!openSet.contains(neighbor)) {
                            openSet.add(neighbor);
                        }
                    }
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private Station getStationByCode(List<Station> stations, String code) {
        for (Station station : stations) {
            if (station.getCode().equals(code)) {
                return station;
            }
        }
        return null; // Handle the case when the station is not found
    }

    private int heuristicEstimate(Station station, Station goal) {
        // You can define your own heuristic, but for counting stops, it's not needed.
        return 0;
    }

    private List<Station> reconstructPath(Map<Station, Station> cameFrom, Station current) {
        List<Station> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // Test the algorithm
        List<Station> stations = new ArrayList<>();
        List<Connection> connections = new ArrayList<>();

        // read stations from csv file
        stations = utils.ReadCsvFile.readStationsWithValidation("data/stations.csv");
        connections = utils.ReadCsvFile.readConnections("data/tracks.csv");

        AStarAlgorithm aStarAlgorithm = new AStarAlgorithm();

        String startCode = "AC";
        String goalCode = "ASHD";

        List<Station> path = aStarAlgorithm.findShortestPath(stations, connections, startCode, goalCode);

        if(path.isEmpty()) {
            System.out.println("No path found");
        } else {
            System.out.println("Path found: ");
            for (Station station : path) {
                System.out.println(station.getSlug());
            }
        }

    }
}
