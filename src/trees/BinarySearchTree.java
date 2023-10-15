package trees;

public class BinarySearchTree<T extends Comparable<T>> implements BasicTree<T> {
    private BinaryTreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return sizeRecursive(root);
    }


    @Override
    public BinaryTreeNode<T> getRoot() {
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
            throw new IllegalArgumentException("Key cannot be null");
        }

        if (isEmpty()) {
            return null;
        }

        if (!contains(key)) {
            return null;
        }

        BinaryTreeNode<T> focusNode = root;
        BinaryTreeNode<T> parent = null; // Initialize parent as null
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
            BinaryTreeNode<T> replacement = getReplacementNode(focusNode);

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
    public BinaryTreeNode<T> get(T key) {
        if(key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return getRecursive(root, key);
    }

    @Override
    public int getTreeHeight() { return getTreeHeightRecursive(root); }

    public String inOrderTraversal(BinaryTreeNode<T> node) {
        StringBuilder sb = new StringBuilder();
        inOrderTraversalRecursive(node, sb);
        return sb.toString();
    }

    public String preOrderTraversal(BinaryTreeNode<T> node) {
        StringBuilder sb = new StringBuilder();
        preOrderTraversalRecursive(node, sb);
        return sb.toString();
    }

    public String postOrderTraversal(BinaryTreeNode<T> node) {
        StringBuilder sb = new StringBuilder();
        postOrderTraversalRecursive(node, sb);
        return sb.toString();
    }

    public boolean isBalanced() { return isBalancedRecursive(root); }


    private int sizeRecursive(BinaryTreeNode<T> current) {
        if (current == null) {
            return 0;
        }

        return 1 + sizeRecursive(current.getLeft()) + sizeRecursive(current.getRight());
    }

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

    private void inOrderTraversalRecursive(BinaryTreeNode<T> node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversalRecursive(node.getLeft(), sb);
            sb.append(node.getData()).append(" ");
            inOrderTraversalRecursive(node.getRight(), sb);
        }
    }

    private void preOrderTraversalRecursive(BinaryTreeNode<T> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.getData()).append(" ");
            preOrderTraversalRecursive(node.getLeft(), sb);
            preOrderTraversalRecursive(node.getRight(), sb);
        }
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

    private boolean isBalancedRecursive(BinaryTreeNode<T> root) {
        if (root == null) {
            return true;
        }

        int leftHeight = getTreeHeightRecursive(root.getLeft());
        int rightHeight = getTreeHeightRecursive(root.getRight());

        return Math.abs(leftHeight - rightHeight) <= 1 && isBalancedRecursive(root.getLeft()) && isBalancedRecursive(root.getRight());
    }

    private BinaryTreeNode<T> getRecursive(BinaryTreeNode<T> root, T key) {
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

    private BinaryTreeNode<T> getReplacementNode(BinaryTreeNode<T> focusNode) {
        BinaryTreeNode<T> replacementParent = focusNode;
        BinaryTreeNode<T> replacement = focusNode;

        BinaryTreeNode<T> current = focusNode.getRight(); // use right child because it is greater than the left child

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
}
