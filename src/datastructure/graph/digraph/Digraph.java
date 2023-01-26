package datastructure.graph.digraph;

import datastructure.graph.AbstractGraph;

import java.io.InputStream;

/**
 * 有向图
 */
public class Digraph extends AbstractGraph {

    public Digraph(int V) {
        super(V);
    }

    public Digraph(InputStream in) {
        super(in);
    }

    /**
     * 添加一个边从v到w的有向边v-w
     *
     * @param v
     * @param w
     */
    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    /**
     * @param v
     * @return 返回与v相邻的所有顶点
     */
    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 遍历每一个结点，然后进行翻转
     *
     * @return 返回翻转后的图
     */
    public Digraph reverse() {
        Digraph digraph = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int v : adj(i)) {
                digraph.addEdge(v, i);
            }
        }
        return digraph;
    }

}
