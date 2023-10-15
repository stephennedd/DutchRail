package trees;

public class BinaryTreeNode<T> {
    private final T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

//    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right, BinaryTreeNode<T> parent) {
//        this.data = data;
//        this.left = left;
//        this.right = right;
//    }

    public T getData() {
        return this.data;
    }

    public BinaryTreeNode<T> getLeft() {
        return this.left;
    }

    public BinaryTreeNode<T> getRight() {
        return this.right;
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

    public BinaryTreeNode<T> rotateLeft() { // balance node by rotating left
        BinaryTreeNode<T> newRoot = this.right;
        this.right = newRoot.getLeft();
        newRoot.setLeft(this);
        return newRoot;
    }

    public BinaryTreeNode<T> rotateRight() { // balance node by rotating right
        BinaryTreeNode<T> newRoot = this.left;
        this.left = newRoot.getRight();
        newRoot.setRight(this);
        return newRoot;
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

}
