package utils.visuals;

import lists.SinglyLinkedList;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTableModelTest {
    private StationTableModel stationTableModel;

    @BeforeEach
    public void setUp() {
        SinglyLinkedList<Station> testData = new SinglyLinkedList<>();
        testData.append(new Station("1", "CODE1", "UIC1", "Name1", "Med1", "Long1", "Slug1", "Country1", "Type1", 10.0, 20.0));
        testData.append(new Station("2", "CODE2", "UIC2", "Name2", "Med2", "Long2", "Slug2", "Country2", "Type2", 30.0, 40.0));

        stationTableModel = new StationTableModel(testData);
    }

    @Test
    public void testGetRowCount() {
        assertEquals(2, stationTableModel.getRowCount());
    }

    @Test
    public void testGetColumnCount() {
        assertEquals(12, stationTableModel.getColumnCount());
    }

    @Test
    public void testGetValueAt() {
        assertEquals(1, stationTableModel.getValueAt(0, 0));
        assertEquals(2, stationTableModel.getValueAt(1, 0));

        assertEquals("1", stationTableModel.getValueAt(0, 1));
        assertEquals("2", stationTableModel.getValueAt(1, 1));

        assertEquals("CODE1", stationTableModel.getValueAt(0, 2));
        assertEquals("CODE2", stationTableModel.getValueAt(1, 2));

        assertEquals("UIC1", stationTableModel.getValueAt(0, 3));
        assertEquals("UIC2", stationTableModel.getValueAt(1, 3));

        assertEquals("Name1", stationTableModel.getValueAt(0, 4));
        assertEquals("Name2", stationTableModel.getValueAt(1, 4));

        assertEquals("Med1", stationTableModel.getValueAt(0, 5));
        assertEquals("Med2", stationTableModel.getValueAt(1, 5));

        assertEquals("Long1", stationTableModel.getValueAt(0, 6));
        assertEquals("Long2", stationTableModel.getValueAt(1, 6));

        assertEquals("Slug1", stationTableModel.getValueAt(0, 7));
        assertEquals("Slug2", stationTableModel.getValueAt(1, 7));

        assertEquals("Country1", stationTableModel.getValueAt(0, 8));
        assertEquals("Country2", stationTableModel.getValueAt(1, 8));

        assertEquals("Type1", stationTableModel.getValueAt(0, 9));
        assertEquals("Type2", stationTableModel.getValueAt(1, 9));

        assertEquals(20.0, stationTableModel.getValueAt(0, 10));
        assertEquals(40.0, stationTableModel.getValueAt(1, 10));

        assertEquals(10.0, stationTableModel.getValueAt(0, 11));
        assertEquals(30.0, stationTableModel.getValueAt(1, 11));

        assertNull(stationTableModel.getValueAt(0, 12));
    }

    @Test
    public void testGetColumnName() {
        assertEquals("#", stationTableModel.getColumnName(0));
        assertEquals("ID", stationTableModel.getColumnName(1));
        assertEquals("CODE", stationTableModel.getColumnName(2));
        assertEquals("UIC", stationTableModel.getColumnName(3));

        // Add more assertions for other columns as needed
    }

}