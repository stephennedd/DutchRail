package utils;

import trees.BasicTree;
import trees.TreeNode;

public class TreeUtils {

    public static <T> boolean isEmpty(BasicTree<T> tree) {
        return tree.getRoot() == null;
    }
    public static <T> int size(BasicTree<T> tree) {
         return sizeRecursive(tree.getRoot());
    }

    public static <T> String inOrderTraversal(BasicTree<T> tree) {
        StringBuilder sb = new StringBuilder();
        inOrderTraversalRecursive(tree.getRoot(), sb);
        return sb.toString();
    }

    public static <T> String postOrderTraversal(BasicTree<T> tree) {
        StringBuilder sb = new StringBuilder();
        postOrderTraversalRecursive(tree.getRoot(), sb);
        return sb.toString();
    }

    public static <T> String preOrderTraversal(BasicTree<T> tree) {
        StringBuilder sb = new StringBuilder();
        preOrderTraversalRecursive(tree.getRoot(), sb);
        return sb.toString();
    }

    public static <T> int getTreeHeight(BasicTree<T> tree) {
        return getTreeHeightRecursive(tree.getRoot());
    }

    public static <T> TreeNode<T> getReplacementNode(TreeNode<T> focusNode) {
        TreeNode<T> replacementParent = focusNode;
        TreeNode<T> replacement = focusNode;

        TreeNode<T> current = focusNode.getRight(); // use right child because it is greater than the left child

        while (current != null) { // find the right child's leftmost child because it is the smallest
            replacementParent = replacement;
            replacement = current;
            current = current.getLeft();
        }

        if (replacement != focusNode.getRight()) { // replacement is not the right child
            replacementParent.setLeft(replacement.getRight());
            replacement.setRight(focusNode.getRight());
        }

        return replacement;
    }

    private static <T> int getTreeHeightRecursive(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getTreeHeightRecursive(root.getLeft()), getTreeHeightRecursive(root.getRight()));
    }

    private static <T> int sizeRecursive(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return 1 + sizeRecursive(root.getLeft()) + sizeRecursive(root.getRight());
    }

    public static <T> boolean isBalancedRecursive(TreeNode<T> root) {
        if (root == null) {
            return true;
        }

        int leftHeight = getTreeHeightRecursive(root.getLeft());
        int rightHeight = getTreeHeightRecursive(root.getRight());

        return Math.abs(leftHeight - rightHeight) <= 1 && isBalancedRecursive(root.getLeft()) && isBalancedRecursive(root.getRight());
    }

    private static <T> void inOrderTraversalRecursive(TreeNode<T> root, StringBuilder sb) {
        if (root != null) {
            inOrderTraversalRecursive(root.getLeft(), sb);
            sb.append(root.getData()).append(" ");
            inOrderTraversalRecursive(root.getRight(), sb);
        }
    }

    private static <T> void postOrderTraversalRecursive(TreeNode<T> node, StringBuilder sb) {
        if (node != null) {
            postOrderTraversalRecursive(node.getLeft(), sb);
            postOrderTraversalRecursive(node.getRight(), sb);
            sb.append(node.getData()).append(" ");
        }
    }

    private static <T> void preOrderTraversalRecursive(TreeNode<T> root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.getData()).append(" ");
            preOrderTraversalRecursive(root.getLeft(), sb);
            preOrderTraversalRecursive(root.getRight(), sb);
        }
    }
}
