package lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestStationHashMap
{

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testStationHashMap() {

    }

    @Test
    public void testEmptyHashMap() {
        StationHashMap map = new StationHashMap();
        assertTrue(map.isEmpty());
        assertEquals(0, map.getCount());
        assertFalse(map.containsKey("model"));
        //assertNull(map.remove("test"));
    }
}
