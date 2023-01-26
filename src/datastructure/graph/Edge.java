package datastructure.graph;



/**
 * 边
 */
public class Edge implements Comparable<Edge> {
    /**
     * 一条边的某一个顶点
     */
    protected final int v;
    /**
     * 一条边的另外一个顶点
     */
    protected final int w;
    /**
     * 边的权重
     */
    protected final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 得到边的某一个顶点
     */
    public int either() {
        return v;
    }

    /**
     * 通过某一顶点得到边的另一个顶点
     */
    public int other(int vertex) {
        if (vertex == w) {
            return v;
        } else if (vertex == v) {
            return w;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }

    public double weight() {
        return weight;
    }


    @Override
    public int compareTo( Edge o) {
        if (this.weight() < o.weight()) {
            return -1;
        } else if (this.weight() > o.weight()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("[%d-%d %.2f]", v, w, weight);
    }
}
