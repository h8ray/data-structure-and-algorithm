package datastructure.graph.undigraph;

import datastructure.graph.AbstractGraph;

import java.io.InputStream;

/**
 * 无向图
 */
public class Undigraph extends AbstractGraph {

    /**
     * 构造一个含有v个顶点的图，当不含边
     */
    public Undigraph(int V) {
        super(V);
    }

    /**
     * 根据输入流构造一个含点含边的图
     */
    public Undigraph(InputStream in) {
        super(in);
    }

    /**
     * 添加一个边v-w
     *
     * @param v
     * @param w
     */
    @Override
    public void addEdge(int v, int w) {
        if (v < 0 || w < 0 || v >= this.V || w >= this.V) {
            return;
        }
        this.adj[v].add(w);
        this.adj[w].add(v);
        this.E++;
    }

    /**
     * @param v
     * @return 返回与v相邻的所有顶点
     */
    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
