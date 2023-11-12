package assignments.screens;

import assignments.Final;
import graphs.TrainStationGraph;
import lists.SinglyLinkedList;
import model.Station;
import utils.visuals.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public class MinSpanningTreePanel extends JPanel {
    SinglyLinkedList<Station> stationList;


    public MinSpanningTreePanel(Final application, SinglyLinkedList<Station> stationList) {
        this.stationList = stationList;

        setLayout(new GridBagLayout());
        setBackground(new Color(0xFFC917));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JComboBox<String> fromStationComboBox = new JComboBox<>();
        JComboBox<String> toStationComboBox = new JComboBox<>();
        CustomButton generateMSTButton = new CustomButton("Generate MST");
        CustomButton backButton = new CustomButton("Back");


        // Populate "from" and "to" station JComboBox with station names
        for (int i = 0; i < stationList.size(); i++) {
            fromStationComboBox.addItem(stationList.get(i).getNameLong());
            toStationComboBox.addItem(stationList.get(i).getNameLong());
        }

        JLabel titleLabel = new JLabel("Select a station for each corner of the rectangle:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Station 1:"), gbc);
        gbc.gridx = 1;
        add(fromStationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Station 2:"), gbc);
        gbc.gridx = 1;
        add(toStationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(backButton, gbc);
        gbc.gridx = 1;
        add(generateMSTButton, gbc);


        backButton.addActionListener(e -> {
            application.showOptionPanel(); // Show the option panel
        });

        generateMSTButton.addActionListener(e -> {
            // get station object with the name from the JComboBox
            Station station1 = stationList.get(fromStationComboBox.getSelectedIndex());
            Station station2 = stationList.get(toStationComboBox.getSelectedIndex());

            // Implement logic to find the shortest route and display information
            if (station1 == null || station2 == null) {
                JOptionPane.showMessageDialog(this, "Please select a station.");
                return;
            } else if (station1.equals(station2)) {
                JOptionPane.showMessageDialog(this, "Please select different stations.");
                return;
            }

            String result = generateMST(station1, station2);
            JOptionPane.showMessageDialog(this, "The MST string has been opened in a new window", "MST", JOptionPane.INFORMATION_MESSAGE);
        });


        setVisible(true);
}

    private String generateMST(Station station1, Station station2) {
        // create a graph with all stations
        TrainStationGraph graph = new TrainStationGraph();

        for (Station station : stationList) {
            graph.addVertex(station);
        }

        // add all edges
        graph.addAllEdges(utils.ReadCsvFile.readConnectionsWithRegex("data/tracks.csv", Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")));

        // create a subgraph with stations within the rectangle
        TrainStationGraph subgraph = TrainStationGraph.filterStationsInRectangle(graph, station1.getLatitude(), station1.getLongitude(), station2.getLatitude(), station2.getLongitude());

        // generate the minimum spanning tree
        TrainStationGraph mst = TrainStationGraph.generateMinimumSpanningTree(subgraph);

        if (mst == null) {
            return "mst cannot be generated because the subgraph is empty or graph is disconnected";
        }  else {
            // Create a new JFrame to display the result
            JFrame resultFrame = new JFrame("MST Result");
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JTextArea to display the result
            JTextArea resultTextArea = new JTextArea();
            resultTextArea.setText(mst.graphViz());
            resultTextArea.setEditable(false);

            // Add the JTextArea to the JFrame
            resultFrame.getContentPane().add(new JScrollPane(resultTextArea));

            // Set the size of the JFrame
            resultFrame.setSize(600, 400); // Adjust the size as needed

            // Center the JFrame on the screen
            resultFrame.setLocationRelativeTo(null);

            // Make the JFrame visible
            resultFrame.setVisible(true);
        }

        return mst.graphViz();

    }
}
