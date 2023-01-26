package datastructure.graph.mst;

import datastructure.graph.Edge;
import datastructure.graph.weightedundigraph.EdgeWeightedUndigraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * prim算法的延时实现
 * 1.把一个顶点加入最小生成树中。
 * 2.再把它的所有的连接着未访问顶点的邻接边都放入优先队列（作为横切边）。
 * 3.然后从横切边中弹出权重最小的一条，检查它两边的顶点是否在最小生成树中，
 * 如果在，则忽略该边，从头开始步骤3。
 * 如果不在，则把它和它所连接的顶点加入最小生成树中。
 * 4.再对它所连接的顶点作和以上相同的操作。直到优先队列为空。
 */
public class LazyPrimMST {
    /**
     * 最小生成树的顶点
     */
    private boolean[] marked;

    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 横切边（包括失效的边）
     */
    private PriorityQueue<Edge> pq;

    /**
     * 最小生成树的总权重
     */
    private double totalWeighted;

    public LazyPrimMST(EdgeWeightedUndigraph graph) {
        marked = new boolean[graph.V()];
        mst = new LinkedList<>();
        pq = new PriorityQueue<>();
        totalWeighted = 0;

        visit(graph, 0);
        while (!pq.isEmpty()) {
            //从pq中得到权重最小的边
            Edge e = pq.poll();

            int v = e.either();
            int w = e.other(v);

            //跳过失效的边
            if (marked[v] && marked[w]) {
                continue;
            }

            //将权重最小的边添加到树中
            mst.offer(e);
            totalWeighted += e.weight();

            //如果顶点v（顶点w）不在树中，那么就visit它
            if (!marked[v]) {
                visit(graph, v);
            }
            if (!marked[w]) {
                visit(graph, w);
            }
        }

        //说明不是连通图，没有最小生成树
        if (mst.size() < graph.V() - 1) {
            mst = null;
            totalWeighted = -1;
        }
    }

    /**
     * 标记顶点v，并将所有连接v和未被标记顶点的边加入pq
     *
     * @param graph
     * @param v
     */
    private void visit(EdgeWeightedUndigraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.offer(e);
            }
        }
    }

    /**
     * 返回最小生成树
     *
     * @return 最小生成树
     */
    public Iterable<Edge> getMst() {
        return this.mst;
    }

    /**
     * 返回最小生成树的总权重
     *
     * @return 最小生成树的总权重
     */
    public double getTotalWeighted() {
        return totalWeighted;
    }
}
