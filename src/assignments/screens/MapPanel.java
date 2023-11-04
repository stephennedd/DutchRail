package assignments.screens;

import assignments.Final;
import utils.CustomButton;

import javax.swing.*;
import java.awt.*;

import static assignments.Final.loadImageAndResize;

public class MapPanel extends JPanel {

    protected CustomButton backButton = new CustomButton("Back");

    public MapPanel(Final application) {
        setBackground(new java.awt.Color(255, 201, 23));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        // create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new java.awt.Color(255, 201, 23));
        buttonPanel.add(backButton);
        add(buttonPanel);

        // create and add title label
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Map of All Stations:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        titlePanel.add(titleLabel);
        add(titlePanel);

        // create and add map image
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel mapLabel = new JLabel();
        ImageIcon icon = loadImageAndResize("data/images/map.jpg", 564, 671);
        mapLabel.setIcon(icon);
        mapPanel.add(mapLabel);
        add(mapPanel);

        // add action listener to back button
        backButton.addActionListener(e -> {
            // Option 5: Back
            application.showOptionPanel();
        });

    }
}
