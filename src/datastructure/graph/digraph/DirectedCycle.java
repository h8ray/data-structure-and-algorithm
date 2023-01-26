package datastructure.graph.digraph;

import datastructure.graph.digraph.Digraph;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;

    /**
     * 顶点是否在递归调用栈上
     */
    private boolean[] onStack;

    /**
     * 有向环中所有顶点
     */
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph digraph) {
        int vNum = digraph.V();
        marked = new boolean[vNum];
        edgeTo = new int[vNum];
        onStack = new boolean[vNum];

        for (int v = 0; v < vNum; v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }

    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : digraph.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;


    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> getCycle() {
        return cycle;
    }
}
