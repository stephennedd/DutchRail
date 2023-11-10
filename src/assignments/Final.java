package assignments;

import assignments.screens.*;
import lists.SinglyLinkedList;
import model.Connection;
import model.Station;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Final {
    private static final String STATIONS_FILE = "data/stations.csv";
    private static final String TRACKS_FILE = "data/tracks.csv";
    private static JFrame frame;
    private final OptionPanel optionPanel;
    private final RoutingPanel routingPanel;
    private final StationsPanel stationsPanel;
    private final MapPanel mapPanel;
    private final ConnectionsPanel connectionsPanel;

    public Final() {
        frame = new JFrame("DutchRail");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Load the stations from the CSV file with regex to ensure the fields are split correctly
        Pattern regex = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Split on commas, but not commas inside quotes
        SinglyLinkedList<Station> stationList = utils.ReadCsvFile.readStationsWithRegex(STATIONS_FILE, regex);
        ArrayList<Connection> connectionList = utils.ReadCsvFile.readConnectionsWithRegex(TRACKS_FILE, regex);

        routingPanel = new RoutingPanel(this, stationList);
        optionPanel = new OptionPanel(this);
        stationsPanel = new StationsPanel(this, stationList);
        mapPanel = new MapPanel(this, stationList);
        connectionsPanel = new ConnectionsPanel(this, connectionList);

        frame.add(optionPanel);
        frame.setVisible(true);
    }

    // Show the routing panel
    public void showRoutingPanel() {
        frame.add(routingPanel);

        stationsPanel.setVisible(false);
        optionPanel.setVisible(false);
        routingPanel.setVisible(true);
        mapPanel.setVisible(false);
        connectionsPanel.setVisible(false);

        frame.revalidate();
    }

    // Show the options panel
    public void showOptionPanel() {
        frame.add(optionPanel);

        stationsPanel.setVisible(false);
        optionPanel.setVisible(true);
        routingPanel.setVisible(false);
        mapPanel.setVisible(false);
        connectionsPanel.setVisible(false);

        frame.revalidate();
    }

    // Show the stations panel
    public void showStationsPanel() {
        frame.add(stationsPanel);

        stationsPanel.setVisible(true);
        optionPanel.setVisible(false);
        routingPanel.setVisible(false);
        mapPanel.setVisible(false);
        connectionsPanel.setVisible(false);

        frame.revalidate();
    }

    // Show the map panel
    public void showMapPanel() {
        frame.add(mapPanel);

        stationsPanel.setVisible(false);
        optionPanel.setVisible(false);
        routingPanel.setVisible(false);
        mapPanel.setVisible(true);
        connectionsPanel.setVisible(false);
        frame.revalidate();
    }

    // Show the connections panel
    public void showConnectionsPanel() {
        frame.add(connectionsPanel);

        stationsPanel.setVisible(false);
        optionPanel.setVisible(false);
        routingPanel.setVisible(false);
        mapPanel.setVisible(false);
        connectionsPanel.setVisible(true);
        frame.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Final::new);
    }

    public static ImageIcon loadImageAndResize(String imagePath, int width, int height) {
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

}
