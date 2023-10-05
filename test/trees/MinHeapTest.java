package trees;

import org.junit.jupiter.api.Test;
import trees.MinHeap;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    public void testMinHeap() {
        MinHeap<Integer> heap = new MinHeap<>(Integer.class,0);
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertNull(heap.peek());
    }

}