package datastructure.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph implements Graph {

    /**
     * 顶点数
     */
    protected int V;

    /**
     * 边数
     */
    protected int E;

    /**
     * 邻接表
     */
    protected List[] adj;

    /**
     * 构造一个含有v个顶点的图，但不含边
     */
    public AbstractGraph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Integer>();
        }
    }

    /**
     * 根据输入流构造一个含点含边的图
     */
    public AbstractGraph(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            int V = Integer.parseInt(reader.readLine());
            int E = Integer.parseInt(reader.readLine());
            this.V = V;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<Integer>();
            }
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s.length != 2) {
                    throw new IOException();
                }
                int v = Integer.parseInt(s[0]);
                int w = Integer.parseInt(s[1]);
                this.addEdge(v, w);
            }
            if (this.E != E) {
                System.err.println("第二行的边数不正确（但不影响得到正确的边数）");
            }
        } catch (IOException e) {
            System.err.println("第一行为顶点数，第二行为边数，第三行开始每行2个数，用空格隔开，2个数表示边的2个顶点（如果是有向图，前一个数为起始点）。");
        }

    }


    /**
     * @return 返回顶点数
     */
    @Override
    public int V() {
        return V;
    }

    /**
     * @return 返回边数
     */
    @Override
    public int E() {
        return E;
    }

    /**
     * 添加一个边v-w
     *
     * @param v
     * @param w
     */
    public abstract void addEdge(int v, int w);

    /**
     * @param v
     * @return 返回与v相邻的所有顶点
     */
    @Override
    public abstract Iterable<Integer> adj(int v);

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Graph{" + "V=" + V + ", E=" + E + "}\n");
        s.append("[\n");
        for (int v = 0; v < V; v++) {
            s.append("  ").append(v).append(": ");
            for (int w : this.adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        s.append("]\n");
        return s.toString();
    }
}
