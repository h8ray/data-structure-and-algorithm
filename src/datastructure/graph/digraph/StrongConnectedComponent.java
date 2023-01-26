package datastructure.graph.digraph;

/**
 * 强连通分量
 */
public class StrongConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int count = 0;

    public StrongConnectedComponent(Digraph digraph) {
        marked = new boolean[digraph.V()];
        id = new int[digraph.V()];
        DepthFirstOrder order = new DepthFirstOrder(digraph.reverse());
        Iterable<Integer> reversePost = order.getReversePost();
        for (int s : reversePost) {
            if (!marked[s]) {
                dfs(digraph, s);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    /**
     * @param v
     * @param w
     * @return v和w是否是强连通
     */
    public boolean isStronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * @return 强连通分量的总数
     */
    public int count() {
        return count;
    }

    /**
     * @param v
     * @return v所在的强连通分量的标识符（在0至count()-1之间）
     */
    public int id(int v) {
        return id[v];
    }
}
