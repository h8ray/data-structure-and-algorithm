package datastructure.tree;

public class TreeNode<E> implements Tree<E> {
    private E val;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode() {
    }

    public TreeNode(E val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "[" + left + ", " + val + "," + right + ']';
    }

    @Override
    public void setVal(E val) {
        this.val = val;
    }

    @Override
    public E getVal() {
        return this.val;
    }

    @Override
    public void setLeft(Tree<E> left) {
        this.left = (TreeNode<E>) left;
    }

    @Override
    public Tree<E> getLeft() {
        return this.left;
    }

    @Override
    public void setRight(Tree<E> right) {
        this.right = (TreeNode<E>) right;
    }

    @Override
    public Tree<E> getRight() {
        return this.right;
    }
}
