package datastructure.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch {
    /**
     * 某顶点是否遍历过
     */
    private boolean[] marked;

    /**
     * 起点
     */
    private int start;

    /**
     * 遍历过的点数
     */
    private int count;

    /**
     * DFS的路径
     */
    private List<Integer> path;

    /**
     * 从起点到一个顶点已知路径上的最后一个点
     */
    private int[] edgeTo;

    /**
     * @param graph 图
     * @param start 遍历的起点
     */
    public DepthFirstSearch(AbstractGraph graph, int start) {
        this.start = start;
        marked = new boolean[graph.V()];
        path = new ArrayList<>();
        edgeTo = new int[graph.V()];
        dfs(graph, start);
    }

    private void dfs(AbstractGraph graph, int s) {
        marked[s] = true;
        path.add(s);
        count++;
        for (int v : graph.adj(s)) {
            if (!marked[v]) {
                edgeTo[v] = s;
                dfs(graph, v);
            }
        }
    }


    public int getCount() {
        return count;
    }

    public Iterable<Integer> getPath() {
        return path;
    }

    /**
     * 从起点是否能到顶点v
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 从起点到v的路径
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int x = v; x != start; x = edgeTo[x]) {
            deque.offerFirst(x);
        }
        deque.offerFirst(start);

        return deque;
    }
}
