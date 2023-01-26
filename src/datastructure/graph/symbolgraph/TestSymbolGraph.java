package datastructure.graph.symbolgraph;

import datastructure.graph.AbstractGraph;
import datastructure.graph.digraph.Digraph;
import datastructure.graph.digraph.Topological;
import datastructure.graph.undigraph.Undigraph;

import java.io.FileNotFoundException;

public class TestSymbolGraph {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "resources/routes.txt";
        String delim = " ";
        SymbolGraph routesGraph = new SymbolGraph(fileName, delim, Undigraph.class);
        AbstractGraph graph = routesGraph.getGraph();
        System.out.println(graph);
        for (int i = 0; i < graph.V(); i++) {
            System.out.print(routesGraph.name(i) + ": ");
            for (int v : graph.adj(i)) {
                System.out.print(routesGraph.name(v) + " ");
            }
            System.out.println();
        }

        fileName = "resources/courses.txt";
        SymbolGraph coursesGraph = new SymbolGraph(fileName, delim, Digraph.class);
        AbstractGraph graph1 = coursesGraph.getGraph();
        System.out.println(graph1);
        Topological top = new Topological((Digraph) graph1);
        System.out.println("课程拓扑排序的结果：");
        for (int v : top.getOrder()) {
            System.out.print(coursesGraph.name(v) + ", ");
        }
    }
}
