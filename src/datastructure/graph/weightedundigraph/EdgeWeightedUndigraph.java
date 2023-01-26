package datastructure.graph.weightedundigraph;

import datastructure.graph.Edge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 加权无向图
 */
public class EdgeWeightedUndigraph {

    /**
     * 顶点总数
     */
    private int V;
    /**
     * 边的总数
     */
    private int E;
    /**
     * 边
     */
    private List<Edge>[] adj;


    /**
     * 构造一个含有v个顶点的图，但不含边
     *
     * @param V
     */
    public EdgeWeightedUndigraph(int V) {
        this.V = V;
        this.E = 0;

        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    public EdgeWeightedUndigraph(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            this.V = Integer.parseInt(reader.readLine());
            E = Integer.parseInt(reader.readLine());
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<Edge>();
            }
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s.length != 3) {
                    throw new IOException();
                }
                int v = Integer.parseInt(s[0]);
                int w = Integer.parseInt(s[1]);
                double weight = Double.parseDouble(s[2]);
                this.addEdge(new Edge(v, w, weight));
            }
            if (this.E != E) {
                System.err.println("第二行的边数不正确（但不影响得到正确的边数）");
            }
        } catch (IOException e) {
            System.err.println("第一行为顶点数，第二行为边数，第三行开始每行3个数，用空格隔开，前2个数表示边的2个顶点（如果是有向图，前一个数为起始点），第3个数表示边的权重。");
        }
    }

    /**
     * @return 返回顶点数
     */
    public int V() {
        return this.V;
    }

    /**
     * @return 返回边数
     */
    public int E() {
        return this.E;
    }

    /**
     * @param v
     * @return 返回与v相邻的所有顶点
     */
    public Iterable<Edge> adj(int v) {
        return this.adj[v];
    }

    /**
     * 添加一条边
     *
     * @param e
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        this.E++;
    }


    /**
     * 获取图中的所有边
     *
     * @return
     */
    public Iterable<Edge> edges() {
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (Edge e : adj[i]) {
                /**
                 * 如果i和j为一条边e，那么adj[i] = e;adj[j] = e;这两条边是一样的，需要去除一条边
                 * 取顶点标号较小的邻接表中的Edge对象
                 * 标号较小的顶点为i，其关于边e的相邻顶点为e.other(i)
                 * 当e.other(i) > i说明第一次遇见边e，将其加入list中
                 */
                if (e.other(i) > i) {
                    edgeList.add(e);
                }
            }
        }
        return edgeList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Graph{" + "V=" + V + ", E=" + E + "}\n");
        s.append("[\n");
        for (int v = 0; v < V; v++) {
            s.append("  ").append(v).append(": ");
            for (Edge e : this.adj(v)) {
                s.append(e).append(" ");
            }
            s.append("\n");
        }
        s.append("]\n");
        return s.toString();
    }


}
