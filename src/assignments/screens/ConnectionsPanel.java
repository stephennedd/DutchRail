package assignments.screens;

import assignments.Final;
import model.Connection;
import utils.visuals.CustomButton;
import utils.visuals.ConnectionTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ConnectionsPanel extends JPanel {
    protected JTable connectionTable;
    protected ArrayList<Connection> connections;
    protected CustomButton backButton = new CustomButton("Back");
    protected CustomButton sortButton = new CustomButton("Sort Connections");
    protected JLabel executionTimeLabel;
    protected DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    protected ConnectionTableModel model;

    public ConnectionsPanel(Final application, ArrayList<Connection> connections) {
        this.connections = connections;
        // add new line to execution label
        //executionTimeLabel = new JLabel("<html>Sorting execution time: <br> (Click sort connections to see execution time)</html>");
        executionTimeLabel = new JLabel();
        executionTimeLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        executionTimeLabel.setHorizontalAlignment(JLabel.RIGHT);
        executionTimeLabel.setVerticalAlignment(JLabel.BOTTOM);

        // move the label to the bottom


        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        setBackground(new Color(0xFFC917));

        // create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0xFFC917));
        buttonPanel.add(backButton);
        buttonPanel.add(sortButton);
        buttonPanel.setPreferredSize(new Dimension(1000, 50));

        // create and add title label
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("List of All Connections:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        titlePanel.setBackground(new Color(0xFFC917));
        titlePanel.add(titleLabel);
        add(titlePanel);

        // create panel for table
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        listPanel.setBackground(new Color(0xFFC917));

        // create table
        ConnectionTableModel model = new ConnectionTableModel(connections);
        connectionTable = new JTable(model);
        connectionTable.setRowHeight(20);
        connectionTable.setFont(new Font("Arial", Font.PLAIN, 13));
        connectionTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        connectionTable.getTableHeader().setForeground(new Color(0x003082));
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        connectionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        connectionTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        connectionTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);


        JScrollPane scrollPane = new JScrollPane(connectionTable);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        listPanel.add(scrollPane);
        add(listPanel);

        add(buttonPanel);
        add(executionTimeLabel);

        backButton.addActionListener(e -> {
            application.showOptionPanel();
        });

        sortButton.addActionListener(e -> {
            sortConnections();
        });
    }

    private void sortConnections() {
        // ask user if they want to sort by insertion or selection
        String[] options = {"Insertion Sort", "Selection Sort"};
        int choice = JOptionPane.showOptionDialog(this, "How would you like to sort the connections?", "Sort Connections", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // ask the user if they want to sort by name or distance in km.
        String[] options2 = {"Name", "Distance in KM"};
        int choice2 = JOptionPane.showOptionDialog(this, "How would you like to sort the connections?", "Sort Connections", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);

        if (choice == 0) {
            insertionSort(choice2);
        } else if (choice == 1) {
            selectionSort(choice2);
        }
    }

    private void insertionSort(int sortBy) {
        // create a copy of the connections list
        ArrayList<Connection> sortedConnections = new ArrayList<>(connections);

        long startTime = System.nanoTime();

        // sort the copy
        for (int i = 1; i < sortedConnections.size(); i++) {
            Connection key = sortedConnections.get(i);
            int j = i - 1;

            switch (sortBy) {
                case 0 -> {
                    while (j >= 0 && sortedConnections.get(j).compareTo(key) > 0) {
                        sortedConnections.set(j + 1, sortedConnections.get(j));
                        j--;
                    }
                }
                case 1 -> {
                    while (j >= 0 && sortedConnections.get(j).compareByDistance(key) > 0) {
                        sortedConnections.set(j + 1, sortedConnections.get(j));
                        j--;
                    }
                }
            }
            sortedConnections.set(j + 1, key);
        }

        long endTime = System.nanoTime();
        executionTimeLabel.setText("Sorting execution time: " + (endTime - startTime) + " nanoseconds");
        executionTimeLabel.setIcon(new ImageIcon("data/images/clock.png"));

        model = new ConnectionTableModel(sortedConnections);
        // update the list
        connectionTable.setModel(model);
        connectionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        connectionTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        connectionTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    }

    private void selectionSort(int sortBy) {
        // create a copy of the connections list
        ArrayList<Connection> sortedConnections = new ArrayList<>(connections);

        long startTime = System.nanoTime();
        // sort the copy
        for (int i = 0; i < sortedConnections.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < sortedConnections.size(); j++) {

                switch (sortBy) {
                    case 0 -> {
                        if (sortedConnections.get(j).compareTo(sortedConnections.get(minIndex)) < 0) {
                            minIndex = j;
                        }
                    }
                    case 1 -> {
                        if (sortedConnections.get(j).compareByDistance(sortedConnections.get(minIndex)) < 0) {
                            minIndex = j;
                        }
                    }
                }
            }

            Connection temp = sortedConnections.get(minIndex);
            sortedConnections.set(minIndex, sortedConnections.get(i));
            sortedConnections.set(i, temp);
        }
        long endTime = System.nanoTime();
        // update the execution time label to show the execution time in nanoseconds
        executionTimeLabel.setText("Sorting execution time: " + (endTime - startTime) + " nanoseconds");
        executionTimeLabel.setIcon(new ImageIcon("data/images/clock.png"));
        // update the list
        connectionTable.setModel(new ConnectionTableModel(sortedConnections));
        connectionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        connectionTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        connectionTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

    }
}
