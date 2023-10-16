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
        return null;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public void put(T data) {

    }

    @Override
    public TreeNode<T> get(T key) {
        return null;
    }

    @Override
    public T remove(T key) {
        return null;
    }

    @Override
    public int getTreeHeight() {
        return 0;
    }

}
