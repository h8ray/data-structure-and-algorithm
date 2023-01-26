package datastructure.tree;

public interface Tree<E> {
    void setVal(E val);

    E getVal();

    void setLeft(Tree<E> left);

    Tree<E> getLeft();

    void setRight(Tree<E> right);

    Tree<E> getRight();
}
