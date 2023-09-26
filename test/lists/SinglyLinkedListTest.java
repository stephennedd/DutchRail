package lists;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Before all");
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.print();
    }

    @Test
    void testEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.getCount());
        assertFalse(list.contains(1));
    }

    @Test
    void testAdd() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertFalse(list.isEmpty());
        list.print();
        assertEquals(3, list.getCount());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }

    @Test
    void testRemove() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2);
        assertFalse(list.contains(2));
        assertEquals(2, list.getCount());
        list.remove(1);
        assertFalse(list.contains(1));
        assertEquals(1, list.getCount());
    }

    @Test
    void testRemoveIfValueIsNotInList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(4);
        assertEquals(3, list.getCount());
    }

   @Test
    void testToArray() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Object[] array = list.toArray();
        assertEquals(3, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
    }

    @Test
    void sorting() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("Zaltbommel");
        list.add("Amsterdam");
        list.add("Utrecht");
        list.add("Rotterdam");
        list.add("Den Haag");
        assertEquals(5, list.getCount());
        assertEquals("Zaltbommel", list.head.data);
        list.sort(Comparator.naturalOrder());
        assertEquals("Amsterdam", list.head.data);
    }
}