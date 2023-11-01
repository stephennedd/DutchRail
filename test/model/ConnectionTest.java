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
        assertEquals("fromStation", connection.fromStation);
        assertEquals("toStation", connection.toStation);
        assertEquals(1, connection.distanceInKm);
        assertEquals(2, connection.distance2);
        assertEquals(3, connection.number3);
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