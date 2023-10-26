package trees;

import utils.TreeUtils;

public class BinarySearchTree<T extends Comparable<T>> implements BasicTree<T> {
    private TreeNode<T> root;

    public BinarySearchTree() {
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
    } // return the root of the tree


    @Override
    public boolean contains(T data) {
        return containsRecursive(root, data);
    } // return true if the tree contains the data


    @Override
    public void put(T data) {
        root = insertRecursive(root, data);
    } // insert data node into the tree

    // https://www.youtube.com/watch?v=UcOxGmj45AA
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

        TreeNode<T> focusNode = root;
        TreeNode<T> parent = null; // Initialize parent as null
        boolean isLeftChild = true;

        while (focusNode.getData().compareTo(key) != 0) {
            parent = focusNode;

            if (key.compareTo(focusNode.getData()) < 0) {
                isLeftChild = true;
                focusNode = focusNode.getLeft();
            } else {
                isLeftChild = false;
                focusNode = focusNode.getRight();
            }
        }

        if (focusNode.isLeaf()) { // node has no children
            if (focusNode == root) {
                root = null;
            } else if (isLeftChild) {
                assert parent != null;
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (!focusNode.hasRight()) { // node has no right child
            if (focusNode == root) {
                root = focusNode.getLeft(); // set the left child as the root
            } else if (isLeftChild) {
                assert parent != null;
                parent.setLeft(focusNode.getLeft());
            } else {
                parent.setRight(focusNode.getLeft());
            }
        } else if (!focusNode.hasLeft()) { // node has no left child
            if (focusNode == root) {
                root = focusNode.getRight(); // set the right child as the root
            } else if (isLeftChild) {
                assert parent != null;
                parent.setLeft(focusNode.getRight());
            } else {
                parent.setRight(focusNode.getRight());
            }
        } else { // node has two children
            TreeNode<T> replacement = TreeUtils.getReplacementNode(focusNode);

            if (focusNode == root) {
                root = replacement;
            } else if (isLeftChild) {
                assert parent != null;
                parent.setLeft(replacement);
            } else {
                parent.setRight(replacement);
            }

            replacement.setLeft(focusNode.getLeft());
        }

        return focusNode.getData();
    }


    @Override
    public TreeNode<T> get(T key) {
        if(key == null) {
            throw new IllegalArgumentException("The key parameter cannot be null");
        }
        return getRecursive(root, key);
    }

    @Override
    public int getTreeHeight() { return TreeUtils.getTreeHeight(this); }


    public boolean isBalanced() { return TreeUtils.isBalancedRecursive(root); }


    private boolean containsRecursive(TreeNode<T> root, T data) {
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

    private TreeNode<T> insertRecursive(TreeNode<T> root, T data) {
        if (root == null) { // empty tree
            return new TreeNode<T>(data);
        }

        if (data.compareTo(root.getData()) < 0) { // data is less than root
            root.setLeft(insertRecursive(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) { // data is greater than root
            root.setRight(insertRecursive(root.getRight(), data));
        }

        return root; // return the unchanged node pointer
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


}
