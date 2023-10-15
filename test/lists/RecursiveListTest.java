package lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveListTest {

    RecursiveList<Integer> recursiveList;

    @BeforeEach
    void setUp() {
        recursiveList = new RecursiveList<>();

    }
    @Test
    void testEmpty() {
        assertTrue(recursiveList.isEmpty());
        recursiveList.append(1);
        assertFalse(recursiveList.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, recursiveList.size());
        recursiveList.append(1);
        assertEquals(1, recursiveList.size());
        recursiveList.append(2);
        assertEquals(2, recursiveList.size());
    }

    @Test
    void testContains() {
        assertFalse(recursiveList.contains(1));
        recursiveList.append(1);
        assertTrue(recursiveList.contains(1));
        recursiveList.append(2);
        assertTrue(recursiveList.contains(2));
        assertFalse(recursiveList.contains(3));
    }

    @Test
    void testAppend() {
        recursiveList.append(1);
        assertEquals(1, recursiveList.size());
        recursiveList.append(2);
        assertEquals(2, recursiveList.size());
        recursiveList.append(3);
        assertEquals(3, recursiveList.size());
    }

    @Test
    void testRemoveFirstValue() {
        recursiveList.append(1);
        recursiveList.append(2);
        recursiveList.remove(1);
        assertEquals(1, recursiveList.size());
        recursiveList.remove(2);
        assertEquals(0, recursiveList.size());
    }

    @Test
    void testRemoveMiddleValue() {
        recursiveList.append(1);
        recursiveList.append(2);
        recursiveList.append(3);
        recursiveList.remove(2);
        assertEquals(2, recursiveList.size());
        recursiveList.remove(3);
        assertEquals(1, recursiveList.size());
    }

    @Test
    void testRemoveFromEmptyList() {
        assertFalse(recursiveList.remove(1));
    }

    @Test
    void testRemoveNonExistingValue() {
        recursiveList.append(1);
        assertFalse(recursiveList.remove(2));
    }

    @Test
    void testPeekTail() {
        recursiveList.append(1);
        assertEquals(1, recursiveList.peekTail());
        recursiveList.append(2);
        assertEquals(2, recursiveList.peekTail());
        recursiveList.append(3);
        assertEquals(3, recursiveList.peekTail());
    }

    @Test
    void testPeekTailEmptyList() {
        assertNull(recursiveList.peekTail());
    }

    @Test
    void testPeekHead() {
        recursiveList.append(1);
        assertEquals(1, recursiveList.peekHead());
        recursiveList.append(2);
        assertEquals(1, recursiveList.peekHead());
        recursiveList.append(3);
        assertEquals(1, recursiveList.peekHead());
    }

    @Test
    void testPeekHeadEmptyList() {
        assertNull(recursiveList.peekHead());
    }

    @Test
    void testGet() {
        recursiveList.append(1);
        assertEquals(1, recursiveList.get(0));
        recursiveList.append(2);
        assertEquals(2, recursiveList.get(1));
        recursiveList.append(3);
        assertEquals(3, recursiveList.get(2));
    }

    @Test
    void testGetEmptyList() {
        assertNull(recursiveList.get(0));
    }

    @Test
    void testGetOutOfBounds() {
        recursiveList.append(1);
        assertThrows(IndexOutOfBoundsException.class, () -> recursiveList.get(1));
    }

    @Test
    void testGetNegativeIndex() {
        recursiveList.append(1);
        assertThrows(IndexOutOfBoundsException.class, () -> recursiveList.get(-1));
    }

    @Test
    void testToString() {
        assertEquals("[]", recursiveList.toString());
        recursiveList.append(1);
        assertEquals("1", recursiveList.toString());
        recursiveList.append(2);
        assertEquals("1, 2", recursiveList.toString());
        recursiveList.append(3);
        assertEquals("1, 2, 3", recursiveList.toString());
    }

}