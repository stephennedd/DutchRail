package utils.visuals;

import model.Connection;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ConnectionTableModel extends AbstractTableModel {
    private final ArrayList<Connection> data;
    private final String[] columnNames = {"FROM", "TO", "DISTANCE"};

    public ConnectionTableModel(ArrayList<Connection> data) {
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
        Connection connection = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return connection.fromStation;
            case 1:
                return connection.toStation;
            case 2:
                return connection.distanceInKm + "KM";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
