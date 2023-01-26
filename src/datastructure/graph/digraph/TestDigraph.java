package datastructure.graph.digraph;


import datastructure.graph.BreadthFirstSearch;
import datastructure.graph.DepthFirstSearch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestDigraph {
    public static void main(String[] args) throws FileNotFoundException {
        Digraph digraph = new Digraph(new FileInputStream("resources\\tinyDG.txt"));
        System.out.println(digraph);

        //遍历的起点
        int start = 6;
        //路径的终点
        int end = 4;

        System.out.println("========DFS=======");

        DepthFirstSearch dfs = new DepthFirstSearch(digraph, start);
        Iterable<Integer> pathDfs = dfs.getPath();
        System.out.println("以" + start + "为起点的DFS结果：" + pathDfs);

        System.out.println("从起点" + start + "到顶点" + end + "的路径：" + dfs.pathTo(end));

        System.out.println("========BFS=======");
        BreadthFirstSearch bfs = new BreadthFirstSearch(digraph, start);
        Iterable<Integer> pathBfs = bfs.getPath();
        System.out.println("以" + start + "为起点的BFS结果：" + pathBfs);
        System.out.println("从起点" + start + "到顶点" + end + "的路径：" + bfs.pathTo(end));

        System.out.println("========是否有环=======");
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        boolean hasCycle = directedCycle.hasCycle();
        System.out.println(hasCycle);
        Iterable<Integer> cycle = directedCycle.getCycle();
        System.out.println("cycle = " + cycle);

        System.out.println("========前序、后序、逆后序=======");
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
        Iterable<Integer> pre = depthFirstOrder.getPre();
        Iterable<Integer> post = depthFirstOrder.getPost();
        Iterable<Integer> reversePost = depthFirstOrder.getReversePost();
        System.out.println("pre = " + pre);
        System.out.println("post = " + post);
        System.out.println("reversePost = " + reversePost);

        System.out.println("========拓扑排序=======");
        Topological topological = new Topological(digraph);
        Iterable<Integer> order = topological.getOrder();
        System.out.println("order = " + order);

        System.out.println("========强连通分量=======");
        StrongConnectedComponent scc = new StrongConnectedComponent(digraph);
        boolean isSC = scc.isStronglyConnected(7,8);
        int count = scc.count();
        int id = scc.id(10);
        System.out.println("isSC = " + isSC);
        System.out.println("count = " + count);
        System.out.println("id = " + id);
    }
}
