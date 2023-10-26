package trees;

public class TreeNode<T> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int height;

    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

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

    public int getHeight() {
        return getHeightRecursive(this);
    }

    private int getHeightRecursive(TreeNode<T> current) {
        if (current == null) {
            return 0;
        }

        return 1 + Math.max(getHeightRecursive(current.getLeft()), getHeightRecursive(current.getRight()));
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void setData(T data) {
        this.data = data;
    }
}
