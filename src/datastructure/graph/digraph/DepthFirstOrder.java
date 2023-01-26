package datastructure.graph.digraph;

import datastructure.graph.digraph.Digraph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 有向图中基于深度优先搜索的顶点排序
 */
public class DepthFirstOrder {
    private boolean[] marked;

    /**
     * 前序
     */
    private Queue<Integer> pre;

    /**
     * 后序
     */
    private Queue<Integer> post;

    /**
     * 逆后序
     */
    private Deque<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new LinkedList<>();

        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }

    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        pre.offer(v);
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
        post.offer(v);
        reversePost.offerFirst(v);
    }

    public Iterable<Integer> getPre(){
        return pre;
    }

    public Iterable<Integer> getPost() {
        return post;
    }

    public Iterable<Integer> getReversePost() {
        return reversePost;
    }
}
