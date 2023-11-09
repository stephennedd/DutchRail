package assignments.screens;

import assignments.Final;
import lists.SinglyLinkedList;
import model.Station;
import utils.visuals.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class StationsPanel extends JPanel {

    protected JList<Station> stationJList;

    protected CustomButton backButton = new CustomButton("Back");
    protected CustomButton SearchButton = new CustomButton("Linear Search");
    protected CustomButton BinarySearchButton = new CustomButton("Binary Search");
    private SinglyLinkedList<Station> stations;
    protected String executionTime;

    public StationsPanel(Final application, SinglyLinkedList<Station> stationList) {
        stations = stationList;
        setLayout(new FlowLayout());
        setBackground(new Color(0xFFC917));

        // create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0xFFC917));
        buttonPanel.add(backButton);
        buttonPanel.add(SearchButton);
        buttonPanel.add(BinarySearchButton);

        // create and add title label
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("List of All Stations:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x003082));
        titlePanel.setBackground(new Color(0xFFC917));
        titlePanel.add(titleLabel);
        add(titlePanel);

        // create panel for list
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        listPanel.setBackground(new Color(0xFFC917));
        stationJList = new JList<>();

        // create vector for list
        Vector<Station> stationVector = new Vector<>();
        for (int i = 0; i < stationList.size(); i++) {
            stationVector.add(stationList.get(i));
        }

        // add vector to list
        stationJList.setListData(stationVector);
        stationJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stationJList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(stationJList);
        listScroller.setPreferredSize(new Dimension(900, 650));
        listPanel.add(listScroller);
        add(listPanel);

        // add button panel to main panel
        add(buttonPanel);

        // add methods to buttons
        backButton.addActionListener(e -> {
            application.showOptionPanel(); // Show the option panel
        });

        SearchButton.addActionListener(e -> {
            searchStation();
        });

        BinarySearchButton.addActionListener(e -> {
            binarySearchStation();
        });
    }

    private void searchStation() {
        // ask user if they want to search by name or by code
        String[] options = {"Name", "Code"};
        int searchBy = JOptionPane.showOptionDialog(this, "Search by name or code?", "Search", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // if user clicks cancel, return
        if (searchBy == -1) {
            return;
        }

        // ask user for station to search for
        String stationToSearch = JOptionPane.showInputDialog("Enter the station " + (searchBy == 0 ? "name" : "code") + " to search for:");

        // if user clicks cancel, return
        if (stationToSearch == null) {
            return;
        }

        // search for station
        long startTime = System.nanoTime();
        int foundStation = Station.linearSearchByNameShortSinglyLinkedList(stations, stationToSearch, searchBy == 0);
        long endTime = System.nanoTime();
        executionTime = String.valueOf((endTime - startTime) / 1000000.0); // convert to milliseconds

        if (foundStation == -1) {
            JOptionPane.showMessageDialog(this, "Station not found", "Search", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Station found at index " + foundStation + " (" + executionTime + " ms)", "Search", JOptionPane.INFORMATION_MESSAGE);
            stationJList.setSelectedIndex(foundStation);
            stationJList.ensureIndexIsVisible(foundStation);
        }
        executionTime = "";
    }

    private void binarySearchStation() {
        // ask user if they want to search by name or by code
        String[] options = {"Name", "Code"};
        int searchBy = JOptionPane.showOptionDialog(this, "Search by name or code?", "Search", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // if user clicks cancel, return
        if (searchBy == -1) {
            return;
        }

        if (searchBy == 1) {
            // sort the stations JList by code
            stations.insertionSort((o1, o2) -> o1.getCode().compareTo(o2.getCode()));

            JOptionPane.showMessageDialog(this, "Stations sorted by code");

            // create vector for list
            Vector<Station> stationsSortedByCode = new Vector<>();
            for (int i = 0; i < stations.size(); i++) {
                stationsSortedByCode.add(stations.get(i));
            }

            // add vector to list
            stationJList.setListData(stationsSortedByCode);
        }

        if (searchBy == 0) {
            // sort the stations JList by name
            stations.insertionSort((o1, o2) -> o1.getNameShort().compareTo(o2.getNameShort()));

            JOptionPane.showMessageDialog(this, "Stations sorted by name");

            // create vector for list
            Vector<Station> stationsSortedByName = new Vector<>();
            for (int i = 0; i < stations.size(); i++) {
                stationsSortedByName.add(stations.get(i));
            }

            // add vector to list
            stationJList.setListData(stationsSortedByName);
        }

        // ask user for station to search for
        String stationToSearch = JOptionPane.showInputDialog("Enter the station " + (searchBy == 0 ? "name" : "code") + " to search for:");

        // if user clicks cancel, return
        if (stationToSearch == null) {
            return;
        }

        // search for station
        long startTime = System.nanoTime();
        int foundIndex = Station.binarySearchSinglyLinkedList(stations, stationToSearch, searchBy == 0);
        long endTime = System.nanoTime();
        executionTime = String.valueOf((endTime - startTime) / 1000000.0); // convert to milliseconds

        if (foundIndex == -1) {
            JOptionPane.showMessageDialog(this, "Station not found", "Search", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Station found at index " + foundIndex + " (" + executionTime + " ms)", "Search", JOptionPane.INFORMATION_MESSAGE);
            stationJList.setSelectedIndex(foundIndex);
            stationJList.ensureIndexIsVisible(foundIndex);
        }

        executionTime = "";
    }


}
