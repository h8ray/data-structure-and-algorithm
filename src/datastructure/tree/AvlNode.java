package datastructure.tree;

public class AvlNode<E> implements Tree<E> {

    /**
     * 允许左右子树高度的最大差值，超过这个值树就不平衡了
     */
    private static final int ALLOWED_IMBALANCE = 1;

    private E val;
    private AvlNode<E> left;
    private AvlNode<E> right;

    /**
     * 高度是从叶子节点往上算的
     * 因此下列有关高度的计算，得先算子节点的高度，再算父节点的高度
     */
    private int height;

    public AvlNode() {
        super();
    }

    public AvlNode(E val) {
        this(val, null, null);
    }

    public AvlNode(E val, AvlNode<E> left, AvlNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.height = 0;
    }


    /**
     * @param t 节点t
     * @return 节点t的高度，如果t为null，返回null
     */
    private int height(AvlNode<E> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * @param x 插入的元素
     * @param t 原先根节点为t的树
     * @return 新树的根节点（根节点可能不是原先的t）
     */
    public AvlNode<E> insert(E x, AvlNode<E> t) {
        if (t == null) {
            return new AvlNode<>(x);
        }

        int compareResult = ((Comparable) x).compareTo(t.val);
        if (compareResult < 0) {
            //x比节点t的值小，递归插入到t的左子树中
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            //x比节点t的值大，递归插入到t的右子树中
            t.right = insert(x, t.right);
        }

        //平衡以节点t为根的树
        return balance(t);
    }

    /**
     * @param x 要删除的元素
     * @param t 原先根节点为t的树
     * @return 新树的根节点（根节点可能不是原先的t）
     */
    public AvlNode<E> remove(E x, AvlNode<E> t) {
        if (t == null) {
            //没有找到x
            return null;
        }

        int compareResult = ((Comparable) x).compareTo(t.val);
        if (compareResult < 0) {
            //x比节点t的值小，递归t的左子树
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            //x比节点t的值大，递归t的右子树
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            //x所在的节点有左右两个儿子

            //左子树不变，从右子树中找到最小的值代替t节点原先的值（从而移除x），再将那个最小值从右子树中移除
            t.val = findMin(t.right).val;
            t.right = remove(t.val, t.right);

            //同理，也可以让右子树不变，从左子树中找打最大的值代替t节点原先的值，再将那个最大值从左子树中移除
           /* t.val = findMax(t.left).val;
            t.left = remove(t.val, t.left);*/
        } else {
            //x坐在的节点只有一个儿子
            t = t.left != null ? t.left : t.right;
        }

        return balance(t);
    }

    private AvlNode<E> findMin(AvlNode<E> t) {
        if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private AvlNode<E> findMax(AvlNode<E> t) {
        if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }


    /**
     * 对以t为根节点的树进行平衡
     */
    private AvlNode<E> balance(AvlNode<E> t) {
        if (t == null) {
            return null;
        }

        int leftHeight = height(t.left);
        int rightHeight = height(t.right);
        if (leftHeight - rightHeight > ALLOWED_IMBALANCE) {
            //左子树高，左*情形

            if (height(t.left.left) >= height(left.right)) {
                //左左情形，单旋转
                t = rotateWithLeftChild(t);
            } else {
                //左右情形，双旋转
                t = doubleWithLeftChild(t);
            }
        } else if (rightHeight - leftHeight > ALLOWED_IMBALANCE) {
            //右子树高，右*情形

            if (height(t.right.right) >= height(t.right.left)) {
                //右右情形，单旋转
                t = rotateWithRightChild(t);
            } else {
                //右左情形，双旋转
                t = doubleWithRightChild(t);
            }
        }

        //更新t的高度
        t.height = Math.max(leftHeight, rightHeight) + 1;
        return t;
    }

    /**
     * 左左单旋转
     *
     * @param k2 原AVL树根
     * @return 新AVL树根
     */
    private AvlNode<E> rotateWithLeftChild(AvlNode<E> k2) {
        /* 旋转前
         *          k2
         *      k1      *
         *    *   *
         *  *
         * 如上图所示，以k2为根的树的左右子树高度差为2，大于1，需要进行左左单旋转
         *
         * 旋转后
         *          k1
         *      *        k2
         *    *        *    *
         */
        AvlNode<E> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }

    /**
     * 右右单旋转
     *
     * @param k2 原AVL树根
     * @return 新AVL树根
     */
    private AvlNode<E> rotateWithRightChild(AvlNode<E> k2) {
        /* 旋转前
         *          k2
         *      *        k1
         *            *      *
         *                      *
         * 如上图所示，以k2为根的树的左右子树高度差为2，大于1，需要进行右右单旋转
         *
         * 旋转后
         *          k1
         *      *        k2
         *    *        *    *
         */
        AvlNode<E> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k2), height(k1.right)) + 1;

        return k1;
    }

    /**
     * 左右双旋转
     *
     * @param k3 原AVL树根
     * @return 新AVL树根
     */
    private AvlNode<E> doubleWithLeftChild(AvlNode<E> k3) {
        /*
         *                      k3
         *              k1              *
         *         *        k2
         *                *     *
         *
         * 先对k1做右右单旋转
         *                      k3
         *              k2              *
         *          k1      *
         *        *    *
         *
         * 再对k3做左左单旋转
         *                      k2
         *              k1              k3
         *           *     *          *    *
         */


        //对左子节点做右右单旋转
        k3.left = rotateWithRightChild(k3.left);

        //对根节点做左左单旋转
        return rotateWithLeftChild(k3);
    }

    /**
     * 右左双旋转
     *
     * @param k3 原AVL树根
     * @return 新AVL树根
     */
    private AvlNode<E> doubleWithRightChild(AvlNode<E> k3) {
        //对右子节点做左左单旋转
        k3.right = rotateWithLeftChild(k3.right);

        //对根节点做右右单旋转
        return rotateWithRightChild(k3);
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
        this.left = (AvlNode<E>) left;
    }

    @Override
    public Tree<E> getLeft() {
        return this.left;
    }

    @Override
    public void setRight(Tree<E> right) {
        this.right = (AvlNode<E>) right;
    }

    @Override
    public Tree<E> getRight() {
        return this.right;
    }

    @Override
    public String toString() {
        return "[" + left + ", " + val + "," + right + ']';
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 7, 16, 15, 14};
        AvlNode<Integer> avlTree = new AvlNode<>(1);
        for (int i : arr) {
            avlTree = avlTree.insert(i, avlTree);
        }
        System.out.println(avlTree);

        avlTree = avlTree.remove(4, avlTree);
        System.out.println(avlTree);

    }
}