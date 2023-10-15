package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {

    private static Connection connection;

    @BeforeAll
    static void setUp() {
        connection = new Connection("fromStation", "toStation", 1, 2, 3);
    }

    @Test
    void testConnection() {
        assertEquals("fromStation", connection.getFromStation());
        assertEquals("toStation", connection.getToStation());
        assertEquals(1, connection.getDistance());
        assertEquals(2, connection.getNumber2());
        assertEquals(3, connection.getNumber3());
    }

    @Test
    void testToString() {
        assertEquals("Connection{fromStation toStation 1 2 3}", connection.toString());
    }

    @Test
    void testCompareTo() {
        Connection connection2 = new Connection("fromStation", "toStation", 1, 2, 3);
        assertEquals(0, connection.compareTo(connection2));
    }
}