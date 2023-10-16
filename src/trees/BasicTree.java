package trees;

public interface BasicTree<T> {

    boolean isEmpty();

    int size();

    TreeNode<T> getRoot();

    boolean contains(T data);

    void put(T data);

    TreeNode<T> get(T key);

    T remove(T key);

    int getTreeHeight();

}
