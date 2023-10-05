package trees;

public interface BasicTree<T> {

    public boolean isEmpty();

    public int getCount();

    public BinaryTreeNode<T> getRoot();

    public boolean contains(T data);

    public void insert(T data);

    public void remove(T data);

    public int getTreeHeight();

    public boolean isBalanced();
}
