package datastructure.graph.undigraph;

import datastructure.graph.BreadthFirstSearch;
import datastructure.graph.ConnectedComponent;
import datastructure.graph.DepthFirstSearch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestUndigraph {
    public static void main(String[] args) throws FileNotFoundException {
        Undigraph graph = new Undigraph(new FileInputStream("resources\\tinyG.txt"));
        System.out.println(graph);

        System.out.println("========DFS=======");
        int start = 0;//遍历的起点
        DepthFirstSearch dfs = new DepthFirstSearch(graph, start);
        Iterable<Integer> pathDfs = dfs.getPath();
        System.out.println("以" + start + "为起点的DFS结果：" + pathDfs);

        int end = 4;//路径的终点
        System.out.println("从起点" + start + "到顶点" + end + "的路径：" + dfs.pathTo(end));

        System.out.println("========BFS=======");
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, start);
        Iterable<Integer> pathBfs = bfs.getPath();
        System.out.println("以" + start + "为起点的BFS结果：" + pathBfs);

        System.out.println("从起点" + start + "到顶点" + end + "的路径：" + bfs.pathTo(end));


        System.out.println("========连通分支=======");
        ConnectedComponent cc = new ConnectedComponent(graph);
        int count = cc.getCount();
        System.out.println("连通分支数：" + count);

        System.out.println(cc.isConnected(0, 2));

        System.out.println(cc.getId(12));

        System.out.println("========是否有环=======");
        Cycle cycle = new Cycle(graph);
        boolean hasCycle = cycle.hasCycle();
        System.out.println(hasCycle);

        System.out.println("========是否是二分图（二部图）=======");
        TwoColor twoColor = new TwoColor(graph);
        boolean isBipartite = twoColor.isBipartite();
        System.out.println(isBipartite);
    }
}
