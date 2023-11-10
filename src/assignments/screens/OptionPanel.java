package assignments.screens;

import assignments.Final;
import utils.visuals.CustomButton;

import javax.swing.*;
import java.awt.*;

import static assignments.Final.loadImageAndResize;

public class OptionPanel extends JPanel {

    public OptionPanel(Final application) {

        // set the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);

        setBackground(new Color(0xFFC917));

        JLabel optionLabel = new JLabel("Welcome to DutchRail");
        optionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        CustomButton shortestRouteButton = new CustomButton("Find Shortest Route");
        CustomButton minimumCostSpanningTreeButton = new CustomButton("Minimum Cost Spanning Tree");
        CustomButton stationsButton = new CustomButton("List of all Stations");
        CustomButton mapButton = new CustomButton("Map of all Stations");
        CustomButton connectionsButton = new CustomButton("List of all Connections");

        JLabel imageLabel = new JLabel();
        ImageIcon icon = loadImageAndResize("data/image.png", 200, 100);
        imageLabel.setIcon(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(imageLabel, gbc);
        gbc.gridy++;
        add(optionLabel, gbc);
        gbc.gridy++;
        add(shortestRouteButton, gbc);
        gbc.gridy++;
        add(mapButton, gbc);
        gbc.gridy++;
        add(stationsButton, gbc);
        gbc.gridy++;
        add(connectionsButton, gbc);
        gbc.gridy++;
        add(minimumCostSpanningTreeButton, gbc);

        shortestRouteButton.addActionListener(e -> {
            // Option 1: Find the shortest Route
            application.showRoutingPanel();
        });

        minimumCostSpanningTreeButton.addActionListener(e -> {
            // TODO: Option 2: Minimum Cost Spanning Tree
        });

        stationsButton.addActionListener(e -> {
            // Option 3: List of all stations
            application.showStationsPanel();
        });

        mapButton.addActionListener(e -> {
            // Option 4: Map of all stations
            application.showMapPanel();
        });

        connectionsButton.addActionListener(e -> {
            // Option 5: List of all connections
            application.showConnectionsPanel();
        });
    }
}
