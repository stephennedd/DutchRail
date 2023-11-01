package assignments.screens;

import assignments.Final;
import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import pathfinding.AStarAlgorithm;
import pathfinding.DijkstraAlgorithm;
import utils.CustomButton;
import utils.ReadCsvFile;
import utils.Sort;

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

        JLabel titleLabel = new JLabel("Select Stations for Routing:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("From Station:"), gbc);
        gbc.gridx = 1;
        add(fromStationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("To Station:"), gbc);
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
            JOptionPane.showMessageDialog(RoutingPanel.this, result);
        });


        setVisible(true);
    }

    private String findShortestRoute(Station fromStation, Station toStation) {
        String result = "";
        List<Station> stations = stationList.toArrayList();
        ArrayList<Connection> connections = ReadCsvFile.readConnections("data/tracks.csv");

        // Ask user if they would like to use Dijkstra's algorithm or A* algorithm
        String[] options = {"Dijkstra's Algorithm", "A* Algorithm"};
        int searchBy = JOptionPane.showOptionDialog(this, "Which algorithm would you like to use?", "Search", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // If user clicks cancel, return
        if (searchBy == -1) {
            return null;
        }

        // If user selects Dijkstra's algorithm, use Dijkstra's algorithm
        if (searchBy == 0) {
            // Implement Dijkstra's algorithm
            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
            Map<Station, Integer> distance = dijkstraAlgorithm.findShortestPath(stations, connections, fromStation.getCode());

            int getDistance = distance.get(toStation);
            if (getDistance == Integer.MAX_VALUE) {
                result = "No route found from " + fromStation.getNameShort() + " to " + toStation.getNameShort() + ".";
            } else {
                result = "Shortest route from " + fromStation.getNameShort() + " to " + toStation.getNameShort() + " is " + getDistance + " km.";
            }
        }

        // If user selects A* algorithm, use A* algorithm
        if (searchBy == 1) {
            // Implement A* algorithm
        }

        // Return a formatted string with route information.
        return result;
    }
}
