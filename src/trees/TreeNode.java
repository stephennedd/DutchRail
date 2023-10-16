package trees;

public class TreeNode<T> {
    private final T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data) {
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

    public TreeNode<T> getLeft() {
        return this.left;
    }

    public TreeNode<T> getRight() {
        return this.right;
    }


    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
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

    public TreeNode<T> rotateLeft() { // balance node by rotating left
        TreeNode<T> newRoot = this.right;
        this.right = newRoot.getLeft();
        newRoot.setLeft(this);
        return newRoot;
    }

    public TreeNode<T> rotateRight() { // balance node by rotating right
        TreeNode<T> newRoot = this.left;
        this.left = newRoot.getRight();
        newRoot.setRight(this);
        return newRoot;
    }

    public int getHeight() {
        return getHeightRecursive(this);
    }

    private int getHeightRecursive(TreeNode<T> current) {
        if (current == null) {
            return 0;
        }

        return 1 + Math.max(getHeightRecursive(current.getLeft()), getHeightRecursive(current.getRight()));
    }

}
