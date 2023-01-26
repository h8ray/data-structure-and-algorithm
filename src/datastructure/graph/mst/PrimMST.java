package datastructure.graph.mst;

import datastructure.graph.Edge;
import datastructure.graph.weightedundigraph.EdgeWeightedUndigraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * prim算法的即时实现
 * 1.先把一个顶点放入最小生成树中。
 * 2.遍历该顶点的邻接边结点（结点A），如果边（边W）所连接的某个顶点（结点B）不在最小生成树中，
 * 且它（结点B）到该顶点（结点A）的距离大于该边（边W）的权重，
 * 则该结点（结点B）到顶点（结点A）的距离变成该边的权重，并且更新索引优先队列中的边。
 * 3.从索引优先队列弹出权重最小的边，然后对它所连接的顶点作以上操作，直到索引优先队列为空。
 */
public class PrimMST {
    /**
     * 距离树最近的边
     */
    private Edge[] edgeTo;

    /**
     * distTo[w] = edgeTo[w].weight()
     */
    private double[] distTo;

    /**
     * 如果v在树中则marked[v]=true
     */
    private boolean[] marked;

    /**
     * 有效的横切边
     */
    private IndexMinPQ<Double> pq;

    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 最小生成树的总权重
     */
    private double totalWeighted;

    public PrimMST(EdgeWeightedUndigraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        pq = new IndexMinPQ<>(graph.V());
        mst = null;
        totalWeighted = 0;

        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(graph, pq.delMin());
        }


        for (int v = 0; v < graph.V(); v++) {
            //有顶点没有被标记，说明不是连通图，没有最小生成树
            if (!marked[v]) {
                totalWeighted = -1;
            }
        }

        if (totalWeighted == 0) {
            mst = new LinkedList<>();
            for (Edge e : edgeTo) {
                if (e != null) {
                    mst.offer(e);
                    totalWeighted += e.weight();
                }
            }
        }
    }

    private void visit(EdgeWeightedUndigraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);

            if (marked[w]) {
                //w已经为最小树的结点,则不进行处理
                //边v-w失效
                continue;
            }

            //如果w结点的权重小于w结点到树的距离
            if (e.weight() < distTo[w]) {
                //连接w和树的最佳边Edge变为e
                edgeTo[w] = e;

                distTo[w] = e.weight();

                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }

            }
        }
    }

    /**
     * 返回最小生成树
     *
     * @return 最小生成树
     */
    public Iterable<Edge> getMst() {
        return mst;
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
