package datastructure.graph.digraph;

/**
 * 拓扑排序
 */
public class Topological {
    /**
     * 顶点的拓扑排序
     */
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle cyclefinder = new DirectedCycle(digraph);
        if (!cyclefinder.hasCycle()) {
            //没有环才能拓扑排序
            DepthFirstOrder dfs = new DepthFirstOrder(digraph);
            //逆后序就是拓扑排序的结果（前提是不存在环）
            order = dfs.getReversePost();
        }
    }

    /**
     * @return 是否是有向无环图（DAG）
     */
    public boolean isDAG() {
        return order != null;
    }

    /**
     * @return 拓扑排序的结果
     */
    public Iterable<Integer> getOrder() {
        return order;
    }
}
