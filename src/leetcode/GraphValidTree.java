package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * For example:
 *
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 *
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 *
 * Note: you can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class GraphValidTree {

    // check if the graph has cycle.
    private boolean hasCycle(Map<Integer, List<Integer>> graph, int node, int parent, boolean[] visited) {
        if (visited[node])
            return true;

        visited[node] = true;

        for (int n : graph.getOrDefault(node, new ArrayList<>())) {
            if (n != parent && hasCycle(graph, n, node, visited))
                return true;
        }

        return false;
    }

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[n];

        // construct the undirected graph
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        if (hasCycle(graph, 0, -1, visited))
            return false;

        // ensure all nodes are visited,
        // otherwise not valid tree
        for (boolean vs : visited) {
            if (!vs)
                return false;
        }

        return true;
    }
}
