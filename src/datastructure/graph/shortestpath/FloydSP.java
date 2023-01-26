package datastructure.graph.shortestpath;

import datastructure.graph.DirectedEdge;
import datastructure.graph.EdgeWeightedDigraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 多源有向可以有负权的最短路径
 */
public class FloydSP {
    /**
     * dist[i][j]表示点i到点j的最短路径
     */
    private double[][] dist;

    /**
     * path[i][j]表示点i到点j最短路径的中转顶点
     */
    private int[][] path;

    public FloydSP(EdgeWeightedDigraph graph) {
        int vNum = graph.V();
        dist = new double[vNum][vNum];
        path = new int[vNum][vNum];

        for (int i = 0; i < vNum; i++) {
            for (int j = 0; j < vNum; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        for (int[] arr : path) {
            Arrays.fill(arr, -1);
        }

        for (int i = 0; i < vNum; i++) {
            for (DirectedEdge e : graph.adj(i)) {
                // i 等于 e.from()
                dist[i][e.to()] = e.weight();
            }
        }

        for (int k = 0; k < vNum; k++) {
            //不断插点

            for (int i = 0; i < vNum; i++) {
                for (int j = 0; j < vNum; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }

    }

    /**
     * 返回一个点到点的存储最短距离的二位数组
     */
    public double[][] getDist() {
        return dist;
    }

    /**
     * 返回从点v到点w的最短距离
     */
    public double dist(int v, int w) {
        return dist[v][w];
    }

    /**
     * 返回一个点到点的最短路径的中转顶点的二位数组
     */
    public int[][] getPath() {
        return path;
    }

    /**
     * 点v到点w是否可达
     */
    public boolean hasPathTo(int v, int w) {
        return dist[v][w] < Double.POSITIVE_INFINITY;
    }

    /**
     * 返回从点v到点w的最短路径经过的顶点
     */
    public Iterable<Integer> path(int v, int w) {
        if (!hasPathTo(v, w)) {
            return null;
        }

        LinkedList<Integer> pathList = new LinkedList<>();
        pathList.add(v);
        pathTrack(pathList, v, w);
        return pathList;
    }

    private void pathTrack(List<Integer> pathList, int v, int w) {
        int k = path[v][w];
        if (k == -1) {
            pathList.add(w);
        } else {
            pathTrack(pathList, v, k);
            pathTrack(pathList, k, w);
        }

    }
}
