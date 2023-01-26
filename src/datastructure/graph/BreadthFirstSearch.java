package datastructure.graph;

import java.util.*;

public class BreadthFirstSearch {
    private boolean[] marked;//某顶点是否遍历过
    private int start; //起点
    private int count;//遍历过的点数
    private List<Integer> path; //BFS的路径
    private int[] edgeTo;//从起点到一个顶点已知路径上的最后一个点

    /**
     * @param graph 图
     * @param start 遍历的起点
     */
    public BreadthFirstSearch(AbstractGraph graph, int start) {
        this.start = start;
        marked = new boolean[graph.V()];
        path = new ArrayList<>();
        edgeTo = new int[graph.V()];
        bfs(graph, start);
    }

    private void bfs(AbstractGraph graph, int s) {
        marked[s] = true;
        path.add(s);
        count++;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    path.add(w);
                    count++;
                    queue.offer(w);
                }
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
