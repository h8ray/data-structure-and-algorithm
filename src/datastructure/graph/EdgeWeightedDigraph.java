package datastructure.graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph implements Graph {

    /**
     * 顶点总数
     */
    protected int V;
    /**
     * 边的总数
     */
    protected int E;
    /**
     * 边
     */
    protected List<DirectedEdge>[] adj;


    /**
     * 构造一个含有v个顶点的图，但不含边
     *
     * @param V
     */
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;

        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<DirectedEdge>();
        }
    }

    public EdgeWeightedDigraph(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            this.V = Integer.parseInt(reader.readLine());
            E = Integer.parseInt(reader.readLine());
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<DirectedEdge>();
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
                this.addEdge(new DirectedEdge(v, w, weight));
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
    @Override
    public int V() {
        return this.V;
    }

    /**
     * @return 返回边数
     */
    @Override
    public int E() {
        return this.E;
    }

    /**
     * 添加一条边
     *
     * @param e
     */
    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        this.E++;
    }

    /**
     * @param v
     * @return 返回与v相邻的所有顶点
     */
    @Override
    public Iterable<DirectedEdge> adj(int v) {
        return this.adj[v];
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Graph{" + "V=" + V + ", E=" + E + "}\n");
        s.append("[\n");
        for (int v = 0; v < V; v++) {
            s.append("  ").append(v).append(": ");
            for (DirectedEdge e : this.adj(v)) {
                s.append(e).append(" ");
            }
            s.append("\n");
        }
        s.append("]\n");
        return s.toString();
    }
}
