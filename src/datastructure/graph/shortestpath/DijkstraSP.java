package datastructure.graph.shortestpath;

import datastructure.graph.DirectedEdge;
import datastructure.graph.EdgeWeightedDigraph;
import datastructure.graph.mst.IndexMinPQ;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单源有向无负权最短路径
 */
public class DijkstraSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        /**
         * 约定 edgeTo[s] = null, distTo[s] = 0, 从起点s到不可达的顶点的距离均为Double.POSITIVE_INFINITY
         */

        distTo = new double[graph.V()];
        edgeTo = new DirectedEdge[graph.V()];
        pq = new IndexMinPQ<>(graph.V());

        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(graph, pq.delMin());
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * 点v到点w是否可达
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Deque<DirectedEdge> queue = new LinkedList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            queue.offerFirst(e);
        }

        return queue;
    }

    /**
     * 顶点的松弛，其实就是从以该顶点为起点的所有邻接边的松弛
     */
    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }


//    /**
//     * 边的松弛
//     */
//    private void relax(DirectedEdge e) {
//        int v = e.from();
//        int w = e.to();
//        //如果从起点s到点w的距离 大于 从s到点v的距离加上边e的权重，那么更新s到w的距离，并且s到w的路径最后经过的边为e
//        if (distTo[w] > distTo[v] + e.weight()) {
//            distTo[w] = distTo[v] + e.weight();
//            edgeTo[w] = e;
//        }
//    }


}
