package datastructure.graph.shortestpath;

import datastructure.graph.DirectedEdge;
import datastructure.graph.EdgeWeightedDigraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestSp {
    public static void main(String[] args) throws FileNotFoundException {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(new FileInputStream("resources\\tinyEWD.txt"));
        System.out.println(graph);

        int v = 6;
        int w = 1;

        System.out.println("=====FloydSP=====");
        FloydSP floydSp = new FloydSP(graph);
        double dist1 = floydSp.dist(v, w);
        Iterable<Integer> path1 = floydSp.path(v, w);
        System.out.println("dist1 = " + dist1);
        System.out.println("path1 = " + path1);

        System.out.println("=====DijkstraSP=====");
        DijkstraSP dijkstraSP = new DijkstraSP(graph, v);
        double dist2 = dijkstraSP.distTo(w);
        Iterable<DirectedEdge> path2 = dijkstraSP.pathTo(w);
        System.out.println("dist2 = " + dist2);
        System.out.println("path2 = " + path2);

        System.out.println("=====DijkstraAllPairsSP=====");
        DijkstraAllPairsSP dijkstraAllPairsSP = new DijkstraAllPairsSP(graph);
        double dist3 = dijkstraAllPairsSP.dist(v, w);
        Iterable<DirectedEdge> path3 = dijkstraAllPairsSP.path(v, w);
        System.out.println("dist3 = " + dist3);
        System.out.println("path3 = " + path3);
    }
}
