package trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ToWebGraphViz;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    private AVLTree<Integer> integerTree;
    private AVLTree<Integer> binarySearchTree;


    @BeforeEach
    void setUp() {
        integerTree = new AVLTree<>();
        binarySearchTree = new AVLTree<>();
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

    @Test
    public void testInsertAndRemove() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.put(10);
        tree.put(5);
        tree.put(15);

        // Test insertion
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));

        // Test removal
        tree.remove(5);
        assertFalse(tree.contains(5));
        tree.remove(10);
        assertFalse(tree.contains(10));
    }

    @Test
    public void testGetAndContains() {
        AVLTree<String> tree = new AVLTree<>();
        tree.put("apple");
        tree.put("banana");
        tree.put("cherry");

        // Test get
        assertEquals(tree.get("apple").getData(), "apple");
        assertEquals(tree.get("banana").getData(), "banana");
        assertEquals(tree.get("cherry").getData(), "cherry");

        // Test contains
        assertTrue(tree.contains("apple"));
        assertFalse(tree.contains("grape"));
    }

    @Test
    public void testEdgeCases() {
        AVLTree<Integer> tree = new AVLTree<>();

        // Test deleting a non-existent node
        tree.put(10);
        tree.put(20);
        tree.put(5);
        tree.remove(15); // 15 doesn't exist, should not affect the tree
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(5));

        // Test deleting the root node
        tree.remove(10);
        assertFalse(tree.contains(10)); // 10 is removed
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(5));

        // Test adding a duplicate node
        tree.put(5);
        assertTrue(tree.contains(5));

        // test removing a node with one child
        tree.remove(5);
        assertFalse(tree.contains(5));
        assertTrue(tree.contains(20));

        // test removing a node with two children
        tree.put(10);
        tree.put(5);
        tree.put(15);
        tree.put(25);
        tree.put(30);
        tree.put(35);
        tree.put(40);
        tree.put(45);
        tree.put(50);
        tree.put(55);
        tree.put(60);
        tree.put(65);

        tree.remove(25);
        assertFalse(tree.contains(25));
        assertTrue(tree.contains(10));
    }

    @Test
    public void testAdding1() {
        AVLTree<String> tree = new AVLTree<>();

        tree.put("alligator");
        tree.put("bat");
        tree.put("cobra");
        tree.put("dog");
        tree.put("horse");
        tree.put("fox");
        tree.put("pig");
        tree.put("leopard");
        tree.put("shark");

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(tree.getRoot());
        String dot = generator.toDotString();
        //System.out.println(dot);

        ToWebGraphViz<String> generator2 = new ToWebGraphViz<>(tree.getRoot());
        tree.put("tiger");
        dot = generator2.toDotString();
        //System.out.println(dot);
    }

    @Test
    public void testAdding2() {
        integerTree.put(30);
        integerTree.put(22);
        integerTree.put(15);
        integerTree.put(10);
        integerTree.put(27);
        integerTree.put(24);
        integerTree.put(29);
        integerTree.put(45);
        integerTree.put(34);
        integerTree.put(26);

        ToWebGraphViz<Integer> generator = new ToWebGraphViz<>(integerTree.getRoot());
        String dot = generator.toDotString();
        //System.out.println(dot);

    }

    @Test
    public void testRemoving1() {
        AVLTree<String> tree = new AVLTree<>();

        tree.put("alligator");
        tree.put("bat");
        tree.put("cobra");
        tree.put("dog");
        tree.put("horse");
        tree.put("fox");
        tree.put("pig");
        tree.put("leopard");
        tree.put("shark");
        tree.put("tiger");

        tree.remove("tiger");

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(tree.getRoot());
        String dot = generator.toDotString();
        //System.out.println(dot);

    }

    @Test
    public void testRemoving2() {
        integerTree.put(30);
        integerTree.put(22);
        integerTree.put(15);
        integerTree.put(10);
        integerTree.put(27);
        integerTree.put(24);
        integerTree.put(29);
        integerTree.put(45);
        integerTree.put(34);
        integerTree.put(26);

        integerTree.remove(26);

        ToWebGraphViz<Integer> generator = new ToWebGraphViz<>(integerTree.getRoot());
        String dot = generator.toDotString();
        //System.out.println(dot);

    }

    @Test
    public void testDeleteLeafNode() {
        binarySearchTree.put(5);
        binarySearchTree.put(3);
        binarySearchTree.put(7);

        binarySearchTree.remove(3);
        assertEquals(2, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(3));
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        binarySearchTree.put(5);
        binarySearchTree.put(3);
        binarySearchTree.put(7);

        binarySearchTree.remove(5);
        assertEquals(2, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(5));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        binarySearchTree.put(5);
        binarySearchTree.put(3);
        binarySearchTree.put(7);
        binarySearchTree.put(2);
        binarySearchTree.put(4);

        binarySearchTree.remove(3);
        assertEquals(4, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(3));
    }

    @Test
    public void testDeleteRootNode() {
        binarySearchTree.put(5);
        binarySearchTree.put(3);
        binarySearchTree.put(7);

        binarySearchTree.remove(5);
        assertEquals(2, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(5));
    }

    @Test
    public void testDeleteNodeInSkewedTree() {
        binarySearchTree.put(1);
        binarySearchTree.put(2);
        binarySearchTree.put(3);

        assertEquals(1, binarySearchTree.remove(1));
        assertEquals(2, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(1));
    }

    @Test
    public void testDeleteNodeInBalancedTree() {
        binarySearchTree.put(2);
        binarySearchTree.put(1);
        binarySearchTree.put(3);

        assertEquals(2, binarySearchTree.remove(2));
        assertEquals(2, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(2));
    }

    @Test
    public void testDeleteNodeWithOnlyLeftChild() {
        binarySearchTree.put(2);
        binarySearchTree.put(1);

        binarySearchTree.remove(2);
        assertEquals(1, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(2));
    }

    @Test
    public void testDeleteNodeWithOnlyRightChild() {
        binarySearchTree.put(1);
        binarySearchTree.put(2);

        binarySearchTree.remove(1);
        assertEquals(1, binarySearchTree.size());
        assertFalse(binarySearchTree.contains(1));
    }

    @Test
    public void testDeleteNonExistingNode() {
        binarySearchTree.put(5);
        binarySearchTree.put(3);
        binarySearchTree.put(7);

        binarySearchTree.remove(10); // non-existing node
        assertEquals(3, binarySearchTree.size());
    }
}