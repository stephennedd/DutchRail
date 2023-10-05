package trees;

public class BinarySearchTree<T extends Comparable<T>> implements BasicTree<T> {
    private BinaryTreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    // return true if the tree is empty
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // return the number of nodes in the tree
    @Override
    public int getCount() {
        return getCountRecursive(root);
    }

    // helper method for getCount()
    private int getCountRecursive(BinaryTreeNode<T> current) {
        if (current.isLeaf()) {
            return 0;
        }

        return 1 + getCountRecursive(current.getLeft()) + getCountRecursive(current.getRight());
    }

    // return the root of the tree
    @Override
    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    // return true if the tree contains the data
    @Override
    public boolean contains(T data) {
        return containsRecursive(root, data);
    }

    // helper method for contains()
    private boolean containsRecursive(BinaryTreeNode<T> root, T data) {
        if (root == null) { // empty tree
            return false;
        }

        if (data.compareTo(root.getData()) == 0) { // data is equal to root
            return true;
        } else if (data.compareTo(root.getData()) < 0) { // data is less than root
            return containsRecursive(root.getLeft(), data);
        } else {
            return containsRecursive(root.getRight(), data); // data is greater than root
        }
    }

    // insert data node into the tree
    @Override
    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    @Override
    public void remove(T data) {
       //TODO: implement remove()
    }

    @Override
    public int getTreeHeight() {
        return getTreeHeightRecursive(root);
    }

    private BinaryTreeNode<T> insertRecursive(BinaryTreeNode<T> root, T data) {
        if (root == null) { // empty tree
            return new BinaryTreeNode<T>(data);
        }

        if (data.compareTo(root.getData()) < 0) { // data is less than root
            root.setLeft(insertRecursive(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) { // data is greater than root
            root.setRight(insertRecursive(root.getRight(), data));
        }

        return root; // return the unchanged node pointer
    }

    public String inOrderTraversal(BinaryTreeNode<T> node) {
        StringBuilder sb = new StringBuilder();
        inOrderTraversalRecursive(node, sb);
        return sb.toString();
    }

    private void inOrderTraversalRecursive(BinaryTreeNode<T> node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversalRecursive(node.getLeft(), sb);
            sb.append(node.getData()).append(" ");
            inOrderTraversalRecursive(node.getRight(), sb);
        }
    }

    public String preOrderTraversal(BinaryTreeNode<T> node) {
        StringBuilder sb = new StringBuilder();
        preOrderTraversalRecursive(node, sb);
        return sb.toString();
    }

    private void preOrderTraversalRecursive(BinaryTreeNode<T> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.getData()).append(" ");
            preOrderTraversalRecursive(node.getLeft(), sb);
            preOrderTraversalRecursive(node.getRight(), sb);
        }
    }

    public String postOrderTraversal(BinaryTreeNode<T> node) {
        StringBuilder sb = new StringBuilder();
        postOrderTraversalRecursive(node, sb);
        return sb.toString();
    }

    private void postOrderTraversalRecursive(BinaryTreeNode<T> node, StringBuilder sb) {
        if (node != null) {
            postOrderTraversalRecursive(node.getLeft(), sb);
            postOrderTraversalRecursive(node.getRight(), sb);
            sb.append(node.getData()).append(" ");
        }
    }

    private int getTreeHeightRecursive(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getTreeHeightRecursive(root.getLeft()), getTreeHeightRecursive(root.getRight()));
    }

    @Override
    public boolean isBalanced() {
        return isBalancedRecursive(root);
    }

    private boolean isBalancedRecursive(BinaryTreeNode<T> root) {
        if (root == null) {
            return true;
        }

        int leftHeight = getTreeHeightRecursive(root.getLeft());
        int rightHeight = getTreeHeightRecursive(root.getRight());

        return Math.abs(leftHeight - rightHeight) <= 1 && isBalancedRecursive(root.getLeft()) && isBalancedRecursive(root.getRight());
    }
}
