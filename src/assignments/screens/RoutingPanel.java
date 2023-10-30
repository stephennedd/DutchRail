package assignments.screens;

import assignments.Final;
import lists.SinglyLinkedList;
import model.Station;
import utils.CustomButton;

import javax.swing.*;
import java.awt.*;

public class RoutingPanel extends JPanel {

    public RoutingPanel(Final application, SinglyLinkedList<Station> stationList) {

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
            String fromStation = (String) fromStationComboBox.getSelectedItem();
            String toStation = (String) toStationComboBox.getSelectedItem();
            // Implement logic to find the shortest route and display information
            String result = findShortestRoute(fromStation, toStation);
            JOptionPane.showMessageDialog(RoutingPanel.this, result);
        });


        setVisible(true);
    }

    private static String findShortestRoute(String fromStation, String toStation) {
        // Implement the logic to find and return the shortest route information.
        // You can use Dijkstra's algorithm or any suitable method for this.
        // Return a formatted string with route information.
        return "Shortest route from " + fromStation + " to " + toStation + " is...";
    }
}
