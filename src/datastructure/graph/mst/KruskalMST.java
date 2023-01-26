package datastructure.graph.mst;

import datastructure.disjointedsets.DisjointedSets;
import datastructure.graph.Edge;
import datastructure.graph.weightedundigraph.EdgeWeightedUndigraph;

import java.util.*;

/**
 * 最小生成树的Kruskal算法实现
 */
public class KruskalMST {
    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 最小生成树的总权重
     */
    private double totalWeighted;

    public KruskalMST(EdgeWeightedUndigraph graph) {
        mst = new LinkedList<>();
        totalWeighted = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((Collection) graph.edges());
        DisjointedSets uf = new DisjointedSets(graph.V());

        //最小生成树的边数比点数少一，因此循环的一个条件是边数mst.size() < 点数-1
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            //从pq中得到权重最小的边和它的顶点
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);

            if (uf.isConnect(v, w)) {
                //忽略失效的边
                continue;
            }

            //合并分量
            uf.union(v, w);

            //将边添加到最小生成中
            mst.add(e);
            totalWeighted += e.weight();
        }

        //说明不是连通图，没有最小生成树
        if (mst.size() < graph.V() - 1) {
            mst = null;
            totalWeighted = -1;
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
