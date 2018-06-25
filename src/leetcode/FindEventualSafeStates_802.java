package leetcode;

import java.util.*;

// In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.
// If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.
//
// Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.
// More specifically, there exists a natural number K so that for any choice of where to walk,
// we must have stopped at a terminal node in less than K steps.
//
// Which nodes are eventually safe?  Return them as an array in sorted order.
//
// The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.
// The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
//
// Example:
// Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
// Output: [2,4,5,6]
// Here is a diagram of the above graph.
public class FindEventualSafeStates_802 {

    // the problem is to find the starting nodes, that do not have a circle path
    // if we detect a circle in this undirected graph, then all the nodes in this path cannot be result
    public List<Integer> eventualSafeNodes(int[][] edges) {
        // build graph

        List<Integer> res = new ArrayList<>();
        int N = edges.length;
        for (int i = 0; i < N; i++) {
            int[] neighbors = edges[i];

            for (int nei : neighbors) {
                graph.computeIfAbsent(i, k -> new ArrayList<>()).add(nei);
            }
        }

        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            dfs(i, visited, new HashSet<>());
        }

        // all nodes except from the non-safe set are results
        for (int i = 0; i < N; i++) {
            if (!nonSafe.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private void dfs(int node, boolean[] visited, HashSet<Integer> ancestor) {
        // if detect a cycle or the node is already in the non-safe list
        // add all the path nodes into non-safe.
        if (ancestor.contains(node) || nonSafe.contains(node)) {
            nonSafe.addAll(ancestor);
            return;
        }

        if (visited[node]) {
            return;
        }

        visited[node] = true;
        ancestor.add(node);
        for (int nei : graph.getOrDefault(node, new ArrayList<>())) {
            dfs(nei, visited, ancestor);
        }

        ancestor.remove(node);
    }

    private HashMap<Integer, List<Integer>> graph = new HashMap<>();
    private HashSet<Integer> nonSafe = new HashSet<>();
}
