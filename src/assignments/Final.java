package assignments;

import model.Station;
import utils.CustomButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Final {
    private static final String STATIONS_FILE = "data/stations.csv";
    private static List<Station> stationList;
    private static JPanel optionPanel;
    private static JPanel routePanel;
    private static JFrame frame;
    public static void main(String[] args) {
        stationList = utils.ReadCsvFile.readStationsWithValidation(STATIONS_FILE); // Load station data from a CSV file

        frame = new JFrame("DutchRail Train Route Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        optionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        optionPanel.setBackground(new Color(0xFFC917));
        frame.add(optionPanel);

        JLabel optionLabel = new JLabel("Welcome to DutchRail");
        CustomButton shortestRouteButton = new CustomButton("Find Shortest Route");
        CustomButton minimumCostSpanningTreeButton = new CustomButton("Minimum Cost Spanning Tree");

        JLabel imageLabel = new JLabel();
        ImageIcon icon =  loadImageAndResize("data/image.png", 50, 20);
        imageLabel.setIcon(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        optionPanel.add(imageLabel, gbc);
        gbc.gridy++;
        optionPanel.add(optionLabel, gbc);
        gbc.gridy++;
        optionPanel.add(shortestRouteButton, gbc);
        gbc.gridy++;
        optionPanel.add(minimumCostSpanningTreeButton, gbc);

        shortestRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Option 1: Find Shortest Route
                optionPanel.setVisible(false); // Hide the option panel
                createRoutePanel(); // Create the panel for entering "from" and "to" stations
            }
        });

        minimumCostSpanningTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Option 2: Minimum Cost Spanning Tree
            }
        });

        frame.setVisible(true);
    }

    private static ImageIcon loadImageAndResize(String imagePath, int width, int height) {
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Resize the image
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Create the panel for entering "from" and "to" stations
    private static void createRoutePanel() {
        routePanel = new JPanel(new GridBagLayout());
        routePanel.setBackground(new Color(0xFFC917));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JComboBox<String> fromStationComboBox = new JComboBox<>();
        JComboBox<String> toStationComboBox = new JComboBox<>();
        CustomButton findRouteButton = new CustomButton("Find Route");
        CustomButton backButton = new CustomButton("Back");


        // Populate "from" and "to" station JComboBox with station names
        for (Station station : stationList) {
            fromStationComboBox.addItem(station.getNameLong());
            toStationComboBox.addItem(station.getNameLong());
        }

        JLabel titleLabel = new JLabel("Select Stations for Routing:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        routePanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        routePanel.add(new JLabel("From Station:"), gbc);
        gbc.gridx = 1;
        routePanel.add(fromStationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        routePanel.add(new JLabel("To Station:"), gbc);
        gbc.gridx = 1;
        routePanel.add(toStationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        routePanel.add(backButton, gbc);
        gbc.gridx = 1;
        routePanel.add(findRouteButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routePanel.setVisible(false); // Hide the route panel
                optionPanel.setVisible(true); // Show the option panel
            }
        });

        findRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromStation = (String) fromStationComboBox.getSelectedItem();
                String toStation = (String) toStationComboBox.getSelectedItem();
                // Implement logic to find the shortest route and display information
                String result = findShortestRoute(fromStation, toStation);
                JOptionPane.showMessageDialog(routePanel, result);
            }
        });

        frame.add(routePanel);
        frame.revalidate();
    }

    private static String findShortestRoute(String fromStation, String toStation) {
        // Implement the logic to find and return the shortest route information.
        // You can use Dijkstra's algorithm or any suitable method for this.
        // Return a formatted string with route information.
        return "Shortest route from " + fromStation + " to " + toStation + " is...";
    }

    private static String calculateMinimumCostSpanningTree(String station1, String station2) {
        // Implement the logic to determine the minimum cost spanning tree.
        // Return a formatted string with the stations included in the tree.
        return "Minimum cost spanning tree for the given rectangle is...";
    }
}
