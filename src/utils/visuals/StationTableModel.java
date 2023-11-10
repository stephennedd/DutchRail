package utils.visuals;
import lists.SinglyLinkedList;
import model.Station;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StationTableModel extends AbstractTableModel {
    private final SinglyLinkedList<Station> data;
    private final String[] columnNames = {"#","ID", "CODE", "UIC", "NAME-SH", "NAME-MED", "NAME-L", "SLUG", "COUNTRY-CODE", "TYPE", "LONGITUDE", "LATITUDE"};

    public StationTableModel(SinglyLinkedList<Station> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Station station = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> rowIndex + 1;
            case 1 -> station.getId();
            case 2 -> station.getCode();
            case 3 -> station.getUic();
            case 4 -> station.getNameShort();
            case 5 -> station.getNameMed();
            case 6 -> station.getNameLong();
            case 7 -> station.getSlug();
            case 8 -> station.getCountryCode();
            case 9 -> station.getType();
            case 10 -> station.getLongitude();
            case 11 -> station.getLatitude();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
