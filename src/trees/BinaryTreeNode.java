package trees;

public class BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    private  BinaryTreeNode<T> parent;

    public BinaryTreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right, BinaryTreeNode<T> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }


    public T getData() {
        return this.data;
    }

    public BinaryTreeNode<T> getLeft() {
        return this.left;
    }

    public BinaryTreeNode<T> getRight() {
        return this.right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public boolean isLeaf() {
        return !hasLeft() && !hasRight();
    }

    public int getHeight() {
        return getHeightRecursive(this);
    }

    private int getHeightRecursive(BinaryTreeNode<T> current) {
        if (current == null) {
            return 0;
        }

        return 1 + Math.max(getHeightRecursive(current.getLeft()), getHeightRecursive(current.getRight()));
    }

    // balance node by rotating left
    public BinaryTreeNode<T> rotateLeft() {
        BinaryTreeNode<T> newRoot = this.right;
        this.right = newRoot.getLeft();
        newRoot.setLeft(this);
        return newRoot;
    }

    // balance node by rotating right
    public BinaryTreeNode<T> rotateRight() {
        BinaryTreeNode<T> newRoot = this.left;
        this.left = newRoot.getRight();
        newRoot.setRight(this);
        return newRoot;
    }


}
