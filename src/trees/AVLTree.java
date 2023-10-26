package trees;

import utils.TreeUtils;

public class AVLTree<T extends Comparable<T>> implements BasicTree<T> {
    private TreeNode<T> root;

    public AVLTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return TreeUtils.isEmpty(this);
    }

    @Override
    public int size() {
        return TreeUtils.size(this);
    }

    @Override
    public TreeNode<T> getRoot() {
        return root;
    }

    @Override
    public boolean contains(T data) {
        return containsRecursive(root, data);
    }

    @Override
    public void put(T data) {
        root = insertRecursive(root, data);
    }

    @Override
    public TreeNode<T> get(T key) {
        return getRecursive(root, key);
    }

    @Override
    public T remove(T key) {
        if (key == null) {
            throw new IllegalArgumentException("The key parameter cannot be null");
        }

        if (isEmpty()) {
            return null;
        }

        if (!contains(key)) {
            return null;
        }

        root = deleteRecursive(root, key);
        return key;
    }

    @Override
    public int getTreeHeight() {
        return getNodeHeight(root);
    }

    private int getNodeHeight(TreeNode<T> node) { // return the height of the node
        return (node == null) ? 0 : node.getHeight();
    }

    private void updateNodeHeight(TreeNode<T> node) { // update the height of the node
        node.setHeight(1 + Math.max(getNodeHeight(node.getLeft()), getNodeHeight(node.getRight())));
    }

    private int getBalanceFactor(TreeNode<T> node) { // return the balance factor of the node
        return (node == null) ? 0 : getNodeHeight(node.getLeft()) - getNodeHeight(node.getRight());
    }

    private TreeNode<T> rotateRight(TreeNode<T> node) { // balance node by rotating right
        TreeNode<T> newRoot = node.getLeft();
        TreeNode<T> temp = newRoot.getRight();

        newRoot.setRight(node);
        node.setLeft(temp);

        updateNodeHeight(node);
        updateNodeHeight(newRoot);

        return newRoot;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> node) { // balance node by rotating left
        TreeNode<T> newRoot = node.getRight();
        TreeNode<T> temp = newRoot.getLeft();

        newRoot.setLeft(node);
        node.setRight(temp);

        updateNodeHeight(node);
        updateNodeHeight(newRoot);

        return newRoot;
    }

    private TreeNode<T> balanceNode(TreeNode<T> node, T data) { // balance the node
        updateNodeHeight(node);

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1 && data.compareTo(node.getLeft().getData()) < 0) { // left-left case
            return rotateRight(node);
        }

        if (balanceFactor < -1 && data.compareTo(node.getRight().getData()) > 0) { // right-right case
            return rotateLeft(node);
        }

        if (balanceFactor > 1 && data.compareTo(node.getLeft().getData()) > 0) { // left-right case
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balanceFactor < -1 && data.compareTo(node.getRight().getData()) < 0) { // right-left case
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private TreeNode<T> insertRecursive(TreeNode<T> node, T data) {
        if (node == null) {
            return new TreeNode<>(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insertRecursive(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(insertRecursive(node.getRight(), data));
        } else {
            return node;
        }

        return balanceNode(node, data);
    }

    private TreeNode<T> getRecursive(TreeNode<T> root, T key) {
        if (root == null) { // empty tree
            return null;
        }

        if (key.compareTo(root.getData()) == 0) { // data is equal to root
            return root;
        } else if (key.compareTo(root.getData()) < 0) { // data is less than root
            return getRecursive(root.getLeft(), key);
        } else {
            return getRecursive(root.getRight(), key); // data is greater than root
        }
    }

    private boolean containsRecursive(TreeNode<T> root, T data) {
        if (root == null) { // empty tree
            return false;
        }

        int comparisonResult = data.compareTo(root.getData());

        if (comparisonResult == 0) { // data is equal to root
            return true;
        } else if (comparisonResult < 0) { // data is less than root
            return containsRecursive(root.getLeft(), data);
        } else {
            return containsRecursive(root.getRight(), data); // data is greater than root
        }
    }

    private TreeNode<T> deleteRecursive(TreeNode<T> node, T key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.getData()) < 0) { // key is less than node
            node.setLeft(deleteRecursive(node.getLeft(), key));
        } else if (key.compareTo(node.getData()) > 0) { // key is greater than node
            node.setRight(deleteRecursive(node.getRight(), key));
        } else { // key is equal to node
            if (node.getLeft() == null || node.getRight() == null) {
                TreeNode<T> temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                TreeNode<T> temp = minValueNode(node.getRight());
                node.setData(temp.getData());
                node.setRight(deleteRecursive(node.getRight(), temp.getData()));
            }
        }

        if (node == null) { // empty tree
            return null;
        }

        updateNodeHeight(node);

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1 && getBalanceFactor(node.getLeft()) >= 0) { // left-left case
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.getRight()) <= 0) { // right-right case
            return rotateLeft(node);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.getLeft()) < 0) { // left-right case
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.getRight()) > 0) { // right-left case
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private TreeNode<T> minValueNode(TreeNode<T> node) {
        TreeNode<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

}
