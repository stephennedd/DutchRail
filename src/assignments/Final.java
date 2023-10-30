package assignments;

import assignments.screens.OptionPanel;
import assignments.screens.RoutingPanel;
import assignments.screens.StationsPanel;
import lists.SinglyLinkedList;
import model.Station;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Final {
    private static final String STATIONS_FILE = "data/stations.csv";
    private static JFrame frame;
    private final OptionPanel optionPanel;
    private final RoutingPanel routingPanel;
    private final StationsPanel stationsPanel;

    public Final() {
        frame = new JFrame("DutchRail Train Route Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Load the stations from the CSV file
        SinglyLinkedList<Station> stationList = utils.ReadCsvFile.readStationsIntoSinglyLinkedList(STATIONS_FILE);

        routingPanel = new RoutingPanel(this, stationList);
        optionPanel = new OptionPanel(this);
        stationsPanel = new StationsPanel(this, stationList);

        frame.add(optionPanel);
        frame.setVisible(true);
    }

    // Show the routing panel
    public void showRoutingPanel() {
        frame.add(routingPanel);

        stationsPanel.setVisible(false);
        optionPanel.setVisible(false);
        routingPanel.setVisible(true);
        frame.revalidate();
    }

    // Show the options panel
    public void showOptionPanel() {
        frame.add(optionPanel);

        stationsPanel.setVisible(false);
        optionPanel.setVisible(true);
        routingPanel.setVisible(false);
        frame.revalidate();
    }

    // Show the stations panel
    public void showStationsPanel() {
        frame.add(stationsPanel);

        optionPanel.setVisible(false);
        routingPanel.setVisible(false);
        stationsPanel.setVisible(true);
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
