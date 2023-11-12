package assignments.screens;

import assignments.Final;
import lists.SinglyLinkedList;
import model.Station;
import utils.visuals.CustomButton;
import utils.visuals.StationTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Vector;

public class StationsPanel extends JPanel {

    protected JTable stationJTable;

    protected CustomButton backButton = new CustomButton("Back");
    protected CustomButton SearchButton = new CustomButton("Linear Search");
    protected CustomButton BinarySearchButton = new CustomButton("Binary Search");
    private SinglyLinkedList<Station> stations;
    protected String executionTime;

    public StationsPanel(Final application, SinglyLinkedList<Station> stationList) {
        stations = stationList;
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
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

        // create panel for table
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        listPanel.setBackground(new Color(0xFFC917));

        // create table
        StationTableModel model = new StationTableModel(stationList);
        stationJTable = new JTable(model);
        stationJTable.setRowHeight(20);
        stationJTable.setFont(new Font("Arial", Font.PLAIN, 12));
        stationJTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        stationJTable.getTableHeader().setForeground(new Color(0x003082));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        stationJTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        stationJTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        stationJTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        stationJTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        stationJTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        stationJTable.getColumn("#").setPreferredWidth(15);
        stationJTable.getColumn("ID").setPreferredWidth(20);
        stationJTable.getColumn("CODE").setPreferredWidth(35);
        stationJTable.getColumn("UIC").setPreferredWidth(45);
        stationJTable.getColumn("NAME-SH").setPreferredWidth(50);
        stationJTable.getColumn("COUNTRY-CODE").setPreferredWidth(25);
        stationJTable.getColumn("LATITUDE").setPreferredWidth(45);
        stationJTable.getColumn("LONGITUDE").setPreferredWidth(45);
        DefaultTableCellRenderer tooltipRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (column == 6) {
                    setToolTipText(stationList.get(row).getNameLong());
                } else if (column == 5) {
                    setToolTipText(stationList.get(row).getNameMed());
                } else if (column == 4) {
                    setToolTipText(stationList.get(row).getNameShort());
                } else if (column == 7) {
                    setToolTipText(stationList.get(row).getSlug());
                } else if (column == 9) {
                    setToolTipText(stationList.get(row).getType());
                }

                return c;
            }

        };

        stationJTable.getColumnModel().getColumn(4).setCellRenderer(tooltipRenderer);
        stationJTable.getColumnModel().getColumn(5).setCellRenderer(tooltipRenderer);
        stationJTable.getColumnModel().getColumn(6).setCellRenderer(tooltipRenderer);
        stationJTable.getColumnModel().getColumn(7).setCellRenderer(tooltipRenderer);
        stationJTable.getColumnModel().getColumn(9).setCellRenderer(tooltipRenderer);


        // add table to scroll pane
        JScrollPane listScroller = new JScrollPane(stationJTable);
        listScroller.setPreferredSize(new Dimension(900, 500));
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
        int foundStation = Station.linearSearchSinglyLinkedList(stations, stationToSearch, searchBy == 0);
        long endTime = System.nanoTime();
        executionTime = String.valueOf((endTime - startTime) / 1000000.0); // convert to milliseconds

        if (foundStation == -1) {
            JOptionPane.showMessageDialog(this, "Station not found", "Search", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Station found at index " + foundStation + " (" + executionTime + " ms)", "Search", JOptionPane.INFORMATION_MESSAGE);
            stationJTable.setRowSelectionInterval(foundStation, foundStation);
            stationJTable.scrollRectToVisible(new Rectangle(stationJTable.getCellRect(foundStation, 0, true)));
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
            // sort the stations JTable by code
            stations.insertionSort((o1, o2) -> o1.getCode().compareTo(o2.getCode()));

            JOptionPane.showMessageDialog(this, "Stations sorted by code");

            stationJTable.setModel(new StationTableModel(stations));
        }

        if (searchBy == 0) {
            // sort the stations JList by name
            stations.insertionSort((o1, o2) -> o1.getNameShort().compareTo(o2.getNameShort()));
            JOptionPane.showMessageDialog(this, "Stations sorted by name");

            stationJTable.setModel(new StationTableModel(stations));
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
            stationJTable.setRowSelectionInterval(foundIndex, foundIndex);
            stationJTable.scrollRectToVisible(new Rectangle(stationJTable.getCellRect(foundIndex, 0, true)));
        }

        executionTime = "";
    }


}
