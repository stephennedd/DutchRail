package trees;

import org.junit.jupiter.api.Test;
import utils.ToWebGraphViz;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void testBinarySearchTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.getCount());
        assertFalse(tree.contains(1));
    }

    @Test
    void testInsert() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        assertFalse(tree.isEmpty());
        assertEquals(3, tree.getCount());
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
    }

    @Test
    void testInOrderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(54);
        tree.insert(27);
        tree.insert(13);
        tree.insert(1);
        tree.insert(44);
        tree.insert(37);
        tree.insert(89);
        tree.insert(71);
        tree.insert(64);
        tree.insert(92);
        String inorder = tree.inOrderTraversal(tree.getRoot());
        assertEquals("1 13 27 37 44 54 64 71 89 92 ", inorder);
    }

    @Test
    void testPreorderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(54);
        tree.insert(27);
        tree.insert(13);
        tree.insert(1);
        tree.insert(44);
        tree.insert(37);
        tree.insert(89);
        tree.insert(71);
        tree.insert(64);
        tree.insert(92);
        String preorder = tree.preOrderTraversal(tree.getRoot());
        assertEquals("54 27 13 1 44 37 89 71 64 92 ", preorder);
    }

    @Test
    void testPostorderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(54);
        tree.insert(27);
        tree.insert(13);
        tree.insert(1);
        tree.insert(44);
        tree.insert(37);
        tree.insert(89);
        tree.insert(71);
        tree.insert(64);
        tree.insert(92);
        String postorder = tree.postOrderTraversal(tree.getRoot());
        assertEquals("1 13 37 44 27 64 71 92 89 54 ", postorder);
    }

    @Test
    void testToWebGraphViz() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("D");
        tree.insert("B");
        tree.insert("F");
        tree.insert("A");
        tree.insert("C");
        tree.insert("E");
        tree.insert("G");

        ToWebGraphViz<String> generator = new ToWebGraphViz<>(tree.getRoot());
        String dot = generator.toDotString();
        System.out.println(dot);

        assertEquals("digraph BinarySearchTree {\n" +
                "  D -> B;\n" +
                "  B -> A;\n" +
                "  B -> C;\n" +
                "  D -> F;\n" +
                "  F -> E;\n" +
                "  F -> G;\n" +
                "}", dot);
    }

}