package datastructure.graph.mst;

import datastructure.graph.Edge;
import datastructure.graph.weightedundigraph.EdgeWeightedUndigraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestMst {
    public static void main(String[] args) throws FileNotFoundException {
        EdgeWeightedUndigraph graph = new EdgeWeightedUndigraph(new FileInputStream("resources\\tinyEWG.txt"));
        System.out.println(graph);

        System.out.println("========Prim延迟实现========");
        LazyPrimMST lazyPrimMst = new LazyPrimMST(graph);
        Iterable<Edge> mst1 = lazyPrimMst.getMst();
        double totalWeighted1 = lazyPrimMst.getTotalWeighted();
        System.out.println("mst1 = " + mst1);
        System.out.println("totalWeighted1 = " + totalWeighted1);

        System.out.println("========Prim即时实现========");
        PrimMST primMst = new PrimMST(graph);
        Iterable<Edge> mst2 = primMst.getMst();
        double totalWeighted2 = primMst.getTotalWeighted();
        System.out.println("mst2 = " + mst2);
        System.out.println("totalWeighted2 = " + totalWeighted2);

        System.out.println("========Kruskal========");
        KruskalMST kruskalMst = new KruskalMST(graph);
        Iterable<Edge> mst3 = kruskalMst.getMst();
        double totalWeighted3 = kruskalMst.getTotalWeighted();
        System.out.println("mst3 = " + mst3);
        System.out.println("totalWeighted3 = " + totalWeighted3);

    }
}
