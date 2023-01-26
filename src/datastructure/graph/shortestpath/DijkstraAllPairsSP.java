package datastructure.graph.shortestpath;

import datastructure.graph.DirectedEdge;
import datastructure.graph.EdgeWeightedDigraph;

public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph graph) {
        all = new DijkstraSP[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            all[v] = new DijkstraSP(graph, v);
        }
    }

    public double dist(int s, int v) {
        return all[s].distTo(v);
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }
}
