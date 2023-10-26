package trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ToWebGraphViz;
import utils.TreeUtils;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;
    private BinarySearchTree<String> stringTree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
        stringTree = new BinarySearchTree<>();
    }

    @Test
    void testBinarySearchTree() {
        assertTrue(tree.isEmpty());
        assertFalse(tree.contains(1));
        assertEquals(0, tree.size());
    }

    @Test
    void testPut() {
        tree.put(2);
        tree.put(1);
        tree.put(3);
        assertFalse(tree.isEmpty());
        assertEquals(3, tree.size());
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
    }

    @Test
    void testRemoveLeftChild() {
        tree.put(2);
        tree.put(1);
        tree.put(3);
        tree.remove(1);
        assertEquals(2, tree.size());
        assertFalse(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
    }

    @Test
    void testRemoveRightChild() {
        tree.put(2);
        tree.put(1);
        tree.put(3);
        tree.remove(3);
        assertEquals(2, tree.size());
        assertFalse(tree.contains(3));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
    }

    @Test
    void testRemoveFromTreeRightNodeWithNoLeftChild() {
        stringTree.put("D");
        stringTree.put("B");
        stringTree.put("C");
        stringTree.put("F");
        stringTree.put("G");
        stringTree.remove("F");
        assertFalse(stringTree.contains("F"));

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(stringTree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);
    }

    @Test
    void testRemoveFromTreeLeftNodeWithNoLeftChild() {
        stringTree.put("D");
        stringTree.put("B");
        stringTree.put("C");
        stringTree.put("F");
        stringTree.put("G");
        stringTree.remove("B");
        assertFalse(stringTree.contains("B"));

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(stringTree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);
    }

    @Test
    void testRemoveFromTreeNodeWithNoRightChild() {
        stringTree.put("D");
        stringTree.put("B");
        stringTree.put("A");
        stringTree.put("F");
        stringTree.put("G");
        stringTree.remove("B");
        assertFalse(stringTree.contains("B"));

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(stringTree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);
    }

    @Test
    void testRemoveLeftChildFromNodeWithOnlyLeftChild() {
        stringTree.put("D");
        stringTree.put("B");
        stringTree.put("A");
        stringTree.put("C");
        stringTree.put("F");
        stringTree.put("E");
        stringTree.remove("F");
        assertFalse(stringTree.contains("F"));

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(stringTree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);
    }

    @Test
    void testRemoveFromNodeWithTwoChildrenRoot() {
        stringTree.put("D");
        stringTree.put("B");
        stringTree.put("A");
        stringTree.put("F");
        stringTree.put("E");
        stringTree.put("G");
        stringTree.remove("D");
        assertFalse(stringTree.contains("D"));
    }
    @Test
    void testRemoveFromNodeWithTwoChildrenRightChild() {
        stringTree.put("D");
        stringTree.put("B");
        stringTree.put("A");
        stringTree.put("F");
        stringTree.put("E");
        stringTree.put("G");
        stringTree.remove("F");
        assertFalse(stringTree.contains("F"));

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(stringTree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);
    }

    @Test
    void testRemoveFromNodeWithTwoChildrenLeftChild() {
        stringTree.put("D");
        stringTree.put("B");
        stringTree.put("A");
        stringTree.put("C");
        stringTree.put("F");
        stringTree.put("E");
        stringTree.put("G");
        stringTree.remove("B");
        assertFalse(stringTree.contains("B"));

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(stringTree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);
    }

    @Test
    void testRootNodeFromTreeWithoutRightChild() {
        stringTree.put("B");
        stringTree.put("A");
        stringTree.remove("B");
        assertFalse(stringTree.contains("B"));

        //System.out.println(stringTree.inOrderTraversal());
    }

    @Test
    void testRootNodeFromTreeWithoutLeftChild() {
        stringTree.put("B");
        stringTree.put("C");
        stringTree.remove("B");
        assertFalse(stringTree.contains("B"));

        //System.out.println(stringTree.inOrderTraversal());
    }

    @Test
    void testRemoveRoot() {
        tree.put(2);
        tree.remove(2);
        assertFalse(tree.contains(2));
    }

    @Test
    void testRemoveNull() {
        assertThrows(IllegalArgumentException.class, () -> tree.remove(null));
    }

    @Test
    void testRemoveFromEmptyTree() {
        assertNull(tree.remove(1));
    }

    @Test
    void testRemoveValueNotFound() {
        tree.put(2);
        tree.put(1);
        tree.put(3);
        assertNull(tree.remove(4));
    }

    @Test
    void testGet() {
        tree.put(2);
        tree.put(1);
        tree.put(3);
        assertEquals(2, tree.get(2).getData());
        assertEquals(1, tree.get(1).getData());
        assertEquals(3, tree.get(3).getData());
    }

    @Test
    void testGetNull() {
        assertThrows(IllegalArgumentException.class, () -> tree.get(null));
    }

    @Test
    void testGetFromEmptyTree() {
        assertNull(tree.get(1));
    }

    @Test
    void testInOrderTraversal() {
        tree.put(54);
        tree.put(27);
        tree.put(13);
        tree.put(1);
        tree.put(44);
        tree.put(37);
        tree.put(89);
        tree.put(71);
        tree.put(64);
        tree.put(92);
        String inorder = TreeUtils.inOrderTraversal(tree);
        assertEquals("1 13 27 37 44 54 64 71 89 92 ", inorder);
    }

    @Test
    void testPreorderTraversal() {
        tree.put(54);
        tree.put(27);
        tree.put(13);
        tree.put(1);
        tree.put(44);
        tree.put(37);
        tree.put(89);
        tree.put(71);
        tree.put(64);
        tree.put(92);
        String preorder = TreeUtils.preOrderTraversal(tree);
        assertEquals("54 27 13 1 44 37 89 71 64 92 ", preorder);
    }

    @Test
    void testPostorderTraversal() {
        tree.put(54);
        tree.put(27);
        tree.put(13);
        tree.put(1);
        tree.put(44);
        tree.put(37);
        tree.put(89);
        tree.put(71);
        tree.put(64);
        tree.put(92);
        String postorder = TreeUtils.postOrderTraversal(tree);
        assertEquals("1 13 37 44 27 64 71 92 89 54 ", postorder);
    }

    @Test
    @DisplayName("Test if the tree is balanced when inserting values D, B, F, A, C, E, G")
    void testInsertBalanced() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("D");
        tree.put("B");
        tree.put("F");
        tree.put("A");
        tree.put("C");
        tree.put("E");
        tree.put("G");
        assertEquals(3, tree.getTreeHeight());

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(tree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);

        assertTrue(tree.isBalanced());
    }

    @Test
    @DisplayName("Test if the tree will be unbalanced when inserting values A, B, C, D, E, F, G")
    void testInsertSortedData() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("A");
        tree.put("C");
        tree.put("B");
        tree.put("D");
        tree.put("E");
        tree.put("F");
        tree.put("G");
        assertEquals(6, tree.getTreeHeight());

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(tree.getRoot());
        String data = generator.toDotString();
        System.out.println(data);

        assertFalse(tree.isBalanced());
    }

    @Test
    @DisplayName("Test if the tree will be unbalanced after inserting in different sequence")
    void testInsertRandomData() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("B");
        tree.put("A");
        tree.put("D");
        tree.put("C");
        tree.put("G");
        tree.put("F");
        tree.put("E");
        //assertEquals(3, tree.getTreeHeight());

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(tree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);

        assertEquals("digraph BinarySearchTree {\n" +
                "  B -> A;\n" +
                "  B -> D;\n" +
                "  D -> C;\n" +
                "  D -> G;\n" +
                "  G -> F;\n" +
                "  F -> E;\n" +
                "}", data);
    }

    @Test
    void testGetNodeHeight() {
        tree.put(54);
        tree.put(27);
        tree.put(13);
        tree.put(1);
        tree.put(44);
        tree.put(37);
        tree.put(89);
        tree.put(71);
        tree.put(64);
        tree.put(92);
        assertEquals(4, tree.get(54).getHeight());
        assertEquals(3, tree.get(27).getHeight());
        assertEquals(2, tree.get(13).getHeight());
        assertEquals(1, tree.get(1).getHeight());

        ToWebGraphViz<Integer> generator = new ToWebGraphViz<>(tree.getRoot());
        String data = generator.toDotString();
        //System.out.println(data);
    }

}