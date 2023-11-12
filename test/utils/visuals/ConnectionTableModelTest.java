package utils.visuals;

import model.Connection;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTableModelTest {
    private ConnectionTableModel connectionTableModel;

    @BeforeEach
    public void setUp() {
        ArrayList<Connection> testData = new ArrayList<>();
        testData.add(new Connection("A", "B", 10, 0, 0));
        testData.add(new Connection("C", "D", 20,0,0));
        testData.add(new Connection("E", "F", 30,0,0));

        connectionTableModel = new ConnectionTableModel(testData);
    }

    @Test
    public void testGetRowCount() {
        assertEquals(3, connectionTableModel.getRowCount());
    }

    @Test
    public void testGetColumnCount() {
        assertEquals(3, connectionTableModel.getColumnCount());
    }

    @Test
    public void testGetValueAt() {
        assertEquals("A", connectionTableModel.getValueAt(0, 0));
        assertEquals("B", connectionTableModel.getValueAt(0, 1));
        assertEquals("10KM", connectionTableModel.getValueAt(0, 2));

        assertEquals("C", connectionTableModel.getValueAt(1, 0));
        assertEquals("D", connectionTableModel.getValueAt(1, 1));
        assertEquals("20KM", connectionTableModel.getValueAt(1, 2));

        assertEquals("E", connectionTableModel.getValueAt(2, 0));
        assertEquals("F", connectionTableModel.getValueAt(2, 1));
        assertEquals("30KM", connectionTableModel.getValueAt(2, 2));

        assertNull(connectionTableModel.getValueAt(0, 3));
    }

    @Test
    public void testGetColumnName() {
        assertEquals("FROM", connectionTableModel.getColumnName(0));
        assertEquals("TO", connectionTableModel.getColumnName(1));
        assertEquals("DISTANCE", connectionTableModel.getColumnName(2));
    }
}