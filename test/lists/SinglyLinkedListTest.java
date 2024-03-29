package lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    // Create a custom comparator for testing purposes
    private final Comparator<Integer> comparator = Comparator.naturalOrder();

    private SinglyLinkedList<Integer> unsortedList;

    @BeforeEach
    public void setUp() {
        // Initialize the unsorted list before each test
        unsortedList = new SinglyLinkedList<>();
    }

    @Test
    void testEmpty() {
        assertTrue(unsortedList.isEmpty());
        assertEquals(0, unsortedList.size());
        assertFalse(unsortedList.contains(1));
    }

    @Test
    void testGetValidIndex() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        assertEquals(1, unsortedList.get(0));
        assertEquals(2, unsortedList.get(1));
        assertEquals(3, unsortedList.get(2));
    }

    @Test
    void getIndexNotValid() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        assertNull(unsortedList.get(4));
    }

    @Test
    public void testEmptyList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        assertNull(list.get(0));
    }

    @Test
    void testAdd() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(5);
        unsortedList.append(4);
        unsortedList.append(3);
        assertFalse(unsortedList.isEmpty());
        unsortedList.toString();
        assertEquals(5, unsortedList.size());
        assertTrue(unsortedList.contains(1));
        assertTrue(unsortedList.contains(2));
        assertTrue(unsortedList.contains(3));
        assertTrue(unsortedList.contains(4));
        assertTrue(unsortedList.contains(5));
    }

    @Test
    void testRemove() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        unsortedList.remove(2);
        assertFalse(unsortedList.contains(2));
        assertEquals(2, unsortedList.size());
        unsortedList.remove(1);
        assertFalse(unsortedList.contains(1));
        assertEquals(1, unsortedList.size());
    }

    @Test
    void testRemoveIfValueIsNotInList() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        unsortedList.remove(4);
        assertEquals(3, unsortedList.size());
    }

   @Test
    void testToArray() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        Object[] array = unsortedList.toArray();
        assertEquals(3, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
    }

    @Test
    void testToArrayList() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        assertEquals(3, unsortedList.toArrayList().size());
        assertEquals(1, unsortedList.toArrayList().get(0));
        assertEquals(2, unsortedList.toArrayList().get(1));
        assertEquals(3, unsortedList.toArrayList().get(2));
    }

    @Test
    void testSortListOfStrings() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.append("Zaltbommel");
        list.append("Amsterdam");
        list.append("Utrecht");
        list.append("Rotterdam");
        list.append("Den Haag");
        assertEquals(5, list.size());
        assertEquals("Zaltbommel", list.head.data);
        list.insertionSort(Comparator.naturalOrder());
        assertEquals("Amsterdam", list.head.data);
    }

    @Test
    public void testSortEmptyList() {
        // Test sorting an empty list
        unsortedList.insertionSort(comparator);
        assertEquals("null", unsortedList.toString());
    }

    @Test
    public void testSortSingleElement() {
        // Test sorting a list with a single element
        unsortedList.append(1);
        unsortedList.insertionSort(comparator);
        assertEquals("1 -> null", unsortedList.toString());
    }

    @Test
    public void testSortSortedList() {
        // Test sorting an already sorted list
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        unsortedList.insertionSort(comparator);
        assertEquals("1 -> 2 -> 3 -> null", unsortedList.toString());
    }

    @Test
    public void testSortUnsortedList() {
        // Test sorting an unsorted list
        unsortedList.append(3);
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(5);
        unsortedList.insertionSort(comparator);
        assertEquals("1 -> 2 -> 3 -> 5 -> null", unsortedList.toString());
    }

    @Test
    public void testQuickSort() {
        // Test sorting an unsorted list
        unsortedList.append(4);
        unsortedList.append(1);
        unsortedList.append(5);
        unsortedList.append(2);
        System.out.println(unsortedList.toString());
        unsortedList.quickSort(comparator);
        System.out.println(unsortedList.toString());
        //assertEquals("1 -> 2 -> 3 -> 5 -> null", unsortedList.toString());
    }

    @Test void testReverseList() {
        unsortedList.append(1);
        unsortedList.append(2);
        unsortedList.append(3);
        unsortedList.append(4);
        unsortedList.append(5);
        SinglyLinkedList<Integer> reversedList = unsortedList.reverseList();
        assertEquals("5 -> 4 -> 3 -> 2 -> 1 -> null", reversedList.toString());
    }
}