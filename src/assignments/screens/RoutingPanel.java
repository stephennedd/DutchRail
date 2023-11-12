package assignments.screens;

import assignments.Final;
import graphs.TrainStationGraph;
import graphs.TrainStationGraphNode;
import graphs.pathfinding.DijkstraAlgorithm;
import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import graphs.pathfinding.AStarAlgorithm;
import utils.visuals.CustomButton;
import utils.ReadCsvFile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutingPanel extends JPanel {
    SinglyLinkedList<Station> stationList;

    public RoutingPanel(Final application, SinglyLinkedList<Station> stationList) {
        this.stationList = stationList;

        setLayout(new GridBagLayout());
        setBackground(new Color(0xFFC917));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JComboBox<String> fromStationComboBox = new JComboBox<>();
        JComboBox<String> toStationComboBox = new JComboBox<>();
        CustomButton findRouteButton = new CustomButton("Find Route");
        CustomButton backButton = new CustomButton("Back");


        // Populate "from" and "to" station JComboBox with station names
        for (int i = 0; i < stationList.size(); i++) {
            fromStationComboBox.addItem(stationList.get(i).getNameLong());
            toStationComboBox.addItem(stationList.get(i).getNameLong());
        }

        JLabel titleLabel = new JLabel("Select Beginning and End Stations:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Begin Station:"), gbc);
        gbc.gridx = 1;
        add(fromStationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("End Station:"), gbc);
        gbc.gridx = 1;
        add(toStationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(backButton, gbc);
        gbc.gridx = 1;
        add(findRouteButton, gbc);

        backButton.addActionListener(e -> {
            application.showOptionPanel(); // Show the option panel
        });

        findRouteButton.addActionListener(e -> {
            // get station object with the name from the JComboBox
            Station fromStationObject = stationList.get(fromStationComboBox.getSelectedIndex());
            Station toStationObject = stationList.get(toStationComboBox.getSelectedIndex());

            // Implement logic to find the shortest route and display information
            if (fromStationObject == null || toStationObject == null) {
                JOptionPane.showMessageDialog(RoutingPanel.this, "Please select a station.");
                return;
            } else if (fromStationObject.equals(toStationObject)) {
                JOptionPane.showMessageDialog(RoutingPanel.this, "Please select different stations.");
                return;
            }
            String result = findShortestRoute(fromStationObject, toStationObject);
            JOptionPane.showMessageDialog(RoutingPanel.this, result, "Route", JOptionPane.INFORMATION_MESSAGE);
        });


        setVisible(true);
    }

    private String findShortestRoute(Station fromStation, Station toStation) {
        StringBuilder result = new StringBuilder();
        ArrayList<Connection> connections = ReadCsvFile.readConnections("data/tracks.csv");

        // Create graph and add vertices and edges
        TrainStationGraph graph = new TrainStationGraph();
        for (Station station: stationList) {
            graph.addVertex(station);
        }
        graph.addAllEdges(connections);

        // get startNode
        TrainStationGraphNode startNode = graph.getNodeByStationCode(fromStation.getCode());

        // Ask user if they would like to use Dijkstra's algorithm or A* algorithm
        String[] options = {"Dijkstra's Algorithm", "A* Algorithm"};
        int searchBy = JOptionPane.showOptionDialog(this, "Which algorithm would you like to use?", "Search", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // If user clicks cancel, return
        if (searchBy == -1) {
            return null;
        }

        // If user selects Dijkstra's algorithm, use Dijkstra's algorithm
        if (searchBy == 0) {
            Map<Station, Integer> shortestDistances = DijkstraAlgorithm.dijkstra(graph, startNode);
            for (Map.Entry<Station, Integer> entry : shortestDistances.entrySet()) {
                if (entry.getKey().getCode().equals(toStation.getCode())) {
                    if (entry.getValue() == Integer.MAX_VALUE) {
                        result.append("No path found.");
                        break;
                    }
                    result.append("\tShortest distance from ").append(fromStation.getNameLong()).append(" to ").append(toStation.getNameLong()).append(" is ").append(entry.getValue()).append(" km.");
                }
            }
        }

        // If user selects A* algorithm, use A* algorithm
        if (searchBy == 1) {
            SinglyLinkedList<Station> shortestPath = AStarAlgorithm.aStar(graph, startNode, graph.getNodeByStationCode(toStation.getCode()));
            if (shortestPath != null) {
                result.append("Shortest path from ").append(fromStation.getNameLong()).append(" to ").append(toStation.getNameLong()).append(":\n");
                for (Station station : shortestPath) {
                    result.append("\t->").append(station.getNameLong()).append("\n");
                }
            } else {
                result.append("No path found.");
            }

        }

        // Return a formatted string with route information.
        return result.toString();
    }
}
