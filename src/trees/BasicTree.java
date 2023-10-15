package trees;

public interface BasicTree<T> {

    boolean isEmpty();

    int size();

    BinaryTreeNode<T> getRoot();

    boolean contains(T data);

    void put(T data);

    BinaryTreeNode<T> get(T key);

    T remove(T key);

    int getTreeHeight();

}
