package datastructure.tree;



import java.util.*;

public class TreeUtil {
    /**
     * @param arr 完全二叉树形式的数组
     * @param i   根节点所在的数组下标
     * @return
     */
    public static <E> Tree<E> buildTree(E [] arr, int i) {
        if (i > arr.length - 1 || arr[i] == null) {
            return null;
        }
        Tree<E> tree = new TreeNode<>(arr[i]);
        tree.setLeft(buildTree(arr, 2 * i + 1));
        tree.setRight(buildTree(arr, 2 * i + 2));
        return tree;
    }

    /**
     * 先序遍历的递归实现
     */
    public static void preOrder(Tree tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.getVal() + " ");
        preOrder(tree.getLeft());
        preOrder(tree.getRight());
    }

    /**
     * 先序遍历的非递归实现
     */
    public static <E> List<E> preOrderTransversal(Tree<E> tree) {
        List<E> ret = new ArrayList<>();

        if (tree == null) {
            return ret;
        }

        Stack<Tree<E>> stack = new Stack<>();
        stack.push(tree);
        while (!stack.empty()) {
            //获取栈顶的节点
            Tree<E> node = stack.pop();

            //栈顶元素即是”中“，加入结果列表
            ret.add(node.getVal());

            //接下去要先遍历左子树，再遍历右子树，因为是栈，得先把右节点压入栈中
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }

        return ret;
    }

    /**
     * 中序遍历的递归实现
     */
    public static void inOrder(Tree tree) {
        if (tree == null) {
            return;
        }
        inOrder(tree.getLeft());
        System.out.print(tree.getVal() + " ");
        inOrder(tree.getRight());
    }

    /**
     * 中序遍历的非递归实现
     */
    public static <E> List<E> inOrderTransversal(Tree<E> tree) {
        List<E> ret = new ArrayList<>();

        Stack<Tree<E>> stack = new Stack<>();
        while (tree != null || !stack.empty()) {
            //一直将左子节点压入栈中，直到没有左子节点
            while (tree != null) {
                stack.push(tree);
                tree = tree.getLeft();
            }


            if (!stack.empty()) {
                //取出栈顶节点
                tree = stack.pop();

                //加入结果列表
                ret.add(tree.getVal());

                //对该节点的右子树进行操作
                tree = tree.getRight();
            }
        }

        return ret;
    }

    /**
     * 后序遍历的递归实现
     */
    public static void postOrder(Tree tree) {
        if (tree == null) {
            return;
        }
        postOrder(tree.getLeft());
        postOrder(tree.getRight());
        System.out.print(tree.getVal() + " ");
    }

    /**
     * 后续遍历的非递归实现
     */
    public static <E> List<E> postOrderTransversal(Tree<E> tree) {
        List<E> ret = new ArrayList<>();

        Stack<Tree<E>> stack = new Stack<>();
        Set<Tree<E>> seen = new HashSet<>();//记录右子树已经遍历（压入栈中）的根

        while (tree != null || !stack.empty()) {
            if (tree == null) {//到某个节点（该节点处于栈顶）后，其没有左子树了
                if (seen.contains(stack.peek())) { //如果该节点的右子树已经遍历完了，那么输出该节点
                    ret.add(stack.pop().getVal());
                } else { //如果该节点的右子树还没遍历，用seen记录该节点，对其右子树遍历
                    seen.add(stack.peek());
                    tree = stack.peek().getRight();
                }
            } else {
                //先压入左子树
                stack.push(tree);
                tree = tree.getLeft();
            }
        }
        return ret;
    }

    /**
     * 获取树的深度
     */
    public static int depthOfTree(Tree tree) {
        if (tree == null) {
            return 0;
        }
        return Math.max(depthOfTree(tree.getLeft()), depthOfTree(tree.getRight())) + 1;
    }

    /**
     * 层序遍历的迭代实现
     */
    public static void levelOrder(Tree tree) {
        int depth = depthOfTree(tree);
        for (int i = 0; i < depth; i++) {
            printLevel(tree, i);
        }
        System.out.println();
    }

    private static void printLevel(Tree tree, int level) {
        if (tree == null) {
            return;
        }
        if (level == 0) {
            System.out.print(tree.getVal() + " ");
        } else {
            printLevel(tree.getLeft(), level - 1);
            printLevel(tree.getRight(), level - 1);
        }
    }

    /**
     * 层序遍历的队列实现
     */
    public static <E> List<E> levelOrderTransversal(Tree<E> tree) {
        List<E> ret = new ArrayList<>();
        if (tree == null) {
            return ret;
        }

        Queue<Tree<E>> queue = new LinkedList<>();

        queue.offer(tree);
        while (!queue.isEmpty()) {
            //队头节点出队
            Tree<E> node = queue.poll();

            //将队头节点加入结果列表
            ret.add(node.getVal());

            //该节点的左子节点不为空，将其加入队列
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }

            //该节点的右子节点不为空，将其加入队列
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }

        return ret;
    }

    /**
     * 树的广度优先搜索就是层序遍历
     */
    public static <E> List<List<E>> treeBFS(Tree<E> tree) {
        if (tree == null) {
            return null;
        }

        List<List<E>> list = new ArrayList<>();
        bfs(tree, 0, list);
        return list;
    }

    private static <E> void bfs(Tree<E> tree, int level, List<List<E>> list) {
        if (tree == null) {
            return;
        }

        if (level >= list.size()) {
            List<E> subList = new ArrayList<>();
            subList.add(tree.getVal());
            list.add(subList);
        } else {
            list.get(level).add(tree.getVal());
        }

        bfs(tree.getLeft(), level + 1, list);
        bfs(tree.getRight(), level + 1, list);
    }

    /**
     * 树的深度度优先搜索就是先序遍历
     */
    public static <E> List<E> treeDFS(Tree<E> tree) {
        if (tree == null) {
            return null;
        }

        List<E> ret = new ArrayList<>();

        Stack<Tree<E>> stack = new Stack<>();
        stack.push(tree);
        while (!stack.empty()) {
            Tree<E> node = stack.pop();
            ret.add(node.getVal());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Character[] arr = {'A', 'B', 'C', 'D', 'E', 'F'};
        Tree<Character> tree = buildTree(arr, 0);
        System.out.println("tree = " + tree);

        List<Character> preAns = preOrderTransversal(tree);
        System.out.println(preAns);
        preOrder(tree);
        System.out.println();

        List<Character> inAns = inOrderTransversal(tree);
        System.out.println(inAns);
        inOrder(tree);
        System.out.println();

        List<Character> postAns = postOrderTransversal(tree);
        System.out.println(postAns);
        postOrder(tree);
        System.out.println();

        List<Character> levelAns = levelOrderTransversal(tree);
        System.out.println(levelAns);
        levelOrder(tree);

        List<List<Character>> bfsAns = treeBFS(tree);
        System.out.println(bfsAns);

        List<Character> dfsAns = treeDFS(tree);
        System.out.println(dfsAns);
    }
}
