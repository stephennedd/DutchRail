package trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    private AVLTree<Integer> integerTree;

    @BeforeEach
    void setUp() {
        integerTree = new AVLTree<>();

    }

    @Test
    void isEmpty() {
        assertTrue(integerTree.isEmpty());
        integerTree.put(1);
        assertFalse(integerTree.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, integerTree.size());
        integerTree.put(10);
        assertEquals(1, integerTree.size());
    }

    @Test
    void getRoot() {
        assertNull(integerTree.getRoot());
        integerTree.put(1);
        assertNotNull(integerTree.getRoot());
    }

    @Test
    void contains() {
        assertFalse(integerTree.contains(1));
        integerTree.put(1);
        assertTrue(integerTree.contains(1));
    }

    @Test
    void get() {
        assertNull(integerTree.get(1));
        integerTree.put(1);
        assertNotNull(integerTree.get(1));
    }

    @Test
    void remove() {
        assertThrows(IllegalArgumentException.class, () -> integerTree.remove(null));
        assertNull(integerTree.remove(1));
        integerTree.put(1);
        assertNull(integerTree.remove(15));
        assertNotNull(integerTree.remove(1));
    }

    @Test
    void getTreeHeight() {
        assertEquals(0, integerTree.getTreeHeight());
        integerTree.put(1);
        assertEquals(1, integerTree.getTreeHeight());
    }
}