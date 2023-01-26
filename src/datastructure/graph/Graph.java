package datastructure.graph;

public interface Graph {

    /**
     * @return 返回顶点数
     */
    int V();

    /**
     * @return 返回边数
     */
    int E();

    /**
     * @param v
     * @return 返回与v相邻的所有顶点
     */
    Iterable adj(int v);
}
