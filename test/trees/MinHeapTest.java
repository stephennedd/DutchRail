package trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trees.MinHeap;
import utils.ToWebGraphViz;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    private MinHeap<Integer> integerHeap;

    @BeforeEach
    void setUp() {
        integerHeap = new MinHeap<>(10);
    }

    @Test
    public void testMinHeap() {
        assertTrue(integerHeap.isEmpty());
        assertEquals(0, integerHeap.size());
        assertNull(integerHeap.peek());
        assertNull(integerHeap.pop());
        integerHeap.push(1);
        integerHeap.pop();
    }

    @Test
    public void testAdding() {
        int[] nums = {13, 21, 24, 65, 26, 31, 32, 16, 19, 68};
        for (int num : nums) {
            integerHeap.push(num);
        }

        assertEquals(13, integerHeap.peek());

        integerHeap.push(14);
        assertEquals(13, integerHeap.peek());
    }

    @Test
    public void testRemovingItems() {
        integerHeap.push(13);
        integerHeap.push(21);
        integerHeap.push(24);
        integerHeap.push(65);
        integerHeap.push(26);
        integerHeap.push(31);
        integerHeap.push(32);
        integerHeap.push(16);
        integerHeap.push(19);
        integerHeap.push(68);
        integerHeap.push(14);
        integerHeap.pop();

        assertEquals(14, integerHeap.peek());
    }

    @Test
    public void testEdgeCases() {
        // Test removing from an empty heap
        assertNull(integerHeap.pop());
        assertNull(integerHeap.peek());

        // Test adding to a full heap
        for (int i = 0; i < 10; i++) {
            integerHeap.push(i);
        }
        //assertThrows(RuntimeException.class, () -> integerHeap.push(10));

        // test remap
        for (int i = 0; i < 10; i++) {
            integerHeap.push(i);
        }
        assertEquals(20, integerHeap.capacity());
        assertEquals(20, integerHeap.size());

        assertEquals(0, integerHeap.pop());
    }
}