package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
 *
 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges.
 * Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 * The answer edge [u, v] should be in the same format, with u < v.
 */
public class RedundantConnection {
    private HashMap<Integer, Set<Integer>> graph = new HashMap<>();

    private boolean hasCycle(int from, int target, int parent) {
        if (graph.getOrDefault(from, new HashSet<>()).contains(target))
            return true;

        for (int node : graph.getOrDefault(from, new HashSet<>())) {
            if (node == parent)
                continue;

            if (hasCycle(node, target, from))
                return true;
        }
        return false;
    }

    // Thought:
    // 1. undirected graph
    // 2. remove an edge, that can make the graph as a tree
    public int[] findRedundantConnection(int[][] edges) {

        for (int[] edge : edges) {
            if (hasCycle(edge[0], edge[1], edge[0]))
                return edge;

            graph.computeIfAbsent(edge[0], key -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], key -> new HashSet<>()).add(edge[0]);
        }

        return new int[]{-1, -1};
    }
}
