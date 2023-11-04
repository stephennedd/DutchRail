package lists;

import model.Station;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void isEmpty() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertFalse(list.contains(1));
    }

    @Test
    void add() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        assertFalse(list.isEmpty());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }

    @Test
    void addSortedInts() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Comparator<Integer> comparator = Comparator.comparing(Integer::intValue);
        list.addSorted(3, comparator);
        list.addSorted(1, comparator);
        list.addSorted(2, comparator);
        assertFalse(list.isEmpty());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertEquals("Nodes of doubly linked list: 1, 2, 3, ", list.toString());
    }

    @Test
    void addSortedStrings() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        Comparator<String> comparator = Comparator.comparing(String::toString);
        list.addSorted("a", comparator);
        list.addSorted("c", comparator);
        list.addSorted("b", comparator);
        assertFalse(list.isEmpty());
        assertTrue(list.contains("a"));
        assertTrue(list.contains("b"));
        assertTrue(list.contains("c"));
        assertEquals("Nodes of doubly linked list: a, b, c, ", list.toString());
    }

    @Test
    void addSortedStationByNameShort() {
        DoublyLinkedList<Station> list = new DoublyLinkedList<>();
        Comparator<Station> comparator = Comparator.comparing(Station::getNameShort);
        assertTrue(list.isEmpty());
        Station station1 = new Station("1", "a", "a", "a", "a", "a", "a", "a", "a", 0.0, 0.0);
        Station station2 = new Station("2", "b", "b", "b", "b", "b", "b", "b", "b", 0.0, 0.0);
        Station station3 = new Station("3", "c", "c", "c", "c", "c", "c", "c", "c", 0.0, 0.0);
        list.addSorted(station1, comparator);
        list.addSorted(station2, comparator);
        list.addSorted(station3, comparator);
        assertFalse(list.isEmpty());
        assertTrue(list.contains(station1));
        assertTrue(list.contains(station2));
        assertTrue(list.contains(station3));
        assertEquals("Nodes of doubly linked list: 1, a, a, a, a, a, a, a, a, 0.0, 0.0, 2, b, b, b, b, b, b, b, b, 0.0, 0.0, 3, c, c, c, c, c, c, c, c, 0.0, 0.0, ", list.toString());
    }

    @Test
    void remove() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertFalse(list.remove(1));
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.remove(1);
        assertFalse(list.contains(1));
        list.remove(3);
        assertFalse(list.contains(3));
        list.remove(5);
        assertFalse(list.contains(5));
        list.remove(2);
        assertFalse(list.contains(2));
        list.remove(4);
        assertFalse(list.contains(4));
    }

    @Test
    void size() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        assertEquals(3, list.size());
    }

    @Test
    void contains() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }

    @Test
    void testToString() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertEquals("DoublyLinkedList is empty", list.toString());
        list.append(1);
        list.append(2);
        list.append(3);
        assertEquals("Nodes of doubly linked list: 1, 2, 3, ", list.toString());
    }
}