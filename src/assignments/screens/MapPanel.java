package assignments.screens;

import assignments.Final;
import lists.SinglyLinkedList;
import model.Station;
import utils.visuals.CustomButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

public class MapPanel extends JPanel {

    private BufferedImage image;
    private final double minLat, maxLat, minLon, maxLon;
    private int imageWidth, imageHeight;
    private SinglyLinkedList<Station> stations;
    private String tooltipText;


    protected CustomButton backButton = new CustomButton("Back");

    public MapPanel(Final application, SinglyLinkedList<Station> stationList) {
        stations = stationList;

        // load map image
        try {
            this.image = ImageIO.read(new File("data/images/map.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        minLat = 50.61;
        maxLat = 53.61;
        minLon = 3.3;
        maxLon = 7.28;

        imageWidth = image.getWidth();
        imageHeight = image.getHeight();

        setBackground(new java.awt.Color(255, 201, 23));
        //setBackground(new java.awt.Color(Color.WHITE.getRGB()));
        setLayout(new FlowLayout());

        // create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //buttonPanel.setBackground(new Color(Color.WHITE.getRGB()));
        buttonPanel.setBackground(new Color(255, 201, 23));
        buttonPanel.add(backButton);
        add(buttonPanel);

        // create and add title label
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Map of All Stations in the Netherlands:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        titlePanel.setBackground(new Color(255, 201, 23));
        titlePanel.add(titleLabel);
        add(titlePanel);


        // add action listener to back button
        backButton.addActionListener(e -> {
            // Option 5: Back
            application.showOptionPanel();
        });

        // Add a mouse motion listener to the panel
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Implement logic to show a tooltip at a specific point
                int mouseX = e.getX();
                int mouseY = e.getY();
                Point coordinate = new Point(mouseX, mouseY);

                isMouseOverCoordinate(coordinate);

                // Repaint the panel to show/hide the tooltip
                repaint();
            }
        });

    }

    private void isMouseOverCoordinate(Point coordinate) {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            Point stationPoint = geoToPixel(station.getLatitude(), station.getLongitude());
            int radius = 3; // Adjust this radius for a larger hover area

            stationPoint.x += 205;
            stationPoint.y += 50;


            if (coordinate.distance(stationPoint) <= radius) {
                tooltipText = "Station: " + station.getNameShort();
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)); // change mouse pointer to crosshair
                return;
            }
        }


        tooltipText = null;
    }

    // Convert latitude and longitude to pixel coordinates
    private Point geoToPixel(double lat, double lon) {
        int x = (int) ((lon - minLon) / (maxLon - minLon) * imageWidth);
        int y = (int) ((maxLat - lat) / (maxLat - minLat) * imageHeight);
        return new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {

            int imageStartX = 200;
            int imageStartY = 45;
            // Draw the image
            g.drawImage(image, imageStartX, imageStartY, this);

            for (int i = 0; i < stations.size(); i++) {
                Station station = stations.get(i);
                Point p = geoToPixel(station.getLatitude(), station.getLongitude());
                if (station.getType().equals("knooppuntIntercitystation")) {
                    g.setColor(Color.GREEN);
                    g.fillOval(p.x + imageStartX, p.y + imageStartY, 8, 8); // size of the knooppuntIntercitystations dot
                } else if (station.getType().equals("megastation")) {
                    g.setColor(Color.BLUE);
                    g.fillOval(p.x + imageStartX, p.y + imageStartY, 10, 10); // size of the megastations dot
                } else {
                    g.setColor(Color.RED);
                    g.fillOval(p.x + imageStartX, p.y + imageStartY, 5, 5); // size of the remaining stations dot
                }
            }

            int screenWidth = 1000;
            int screenHeight = 800;

            // Draw the tooltip
            if (tooltipText != null) {
                int tooltipX = getMousePosition().x + 5;
                int tooltipY = getMousePosition().y + 5;

                // If the tooltip is too close to the right edge of the screen, move it to the left
                if (tooltipX + 150 > screenWidth) {
                    tooltipX = getMousePosition().x - 155;
                }
                // If the tooltip is too close to the bottom edge of the screen, move it up
                if (tooltipY + 20 > screenHeight) {
                    tooltipY = getMousePosition().y - 25;
                }

                g.setColor(Color.WHITE);
                g.fillRect(tooltipX, tooltipY, 150, 20);
                g.setColor(Color.BLACK);
                g.drawString(tooltipText, tooltipX + 5, tooltipY + 15);
            }
        }
    }
}
