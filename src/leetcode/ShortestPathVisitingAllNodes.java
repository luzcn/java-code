package leetcode;

import java.util.*;

// An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
//
// graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
//
// Return the length of the shortest path that visits every node.
// You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
//
//
//
// Example 1:
//
// Input: [[1,2,3],[0],[0],[0]]
// Output: 4
// Explanation: One possible path is [1,0,2,0,3]
//
// Example 2:
//
// Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
// Output: 4
// Explanation: One possible path is [0,1,4,2,3]
public class ShortestPathVisitingAllNodes {

    private HashMap<Integer, List<Integer>> graph = new HashMap<>();
    private HashMap<Integer, Integer> indegree = new HashMap<>();

    private int res = Integer.MAX_VALUE;

    public int shortestPathLength(int[][] edges) {

        // build undirected graph
        for (int i = 0; i < edges.length; i++) {
            for (int node : edges[i]) {
                graph.computeIfAbsent(i, k -> new ArrayList<>()).add(node);
                // graph.computeIfAbsent(node, k -> new ArrayList<>()).add(i);
                indegree.put(node, indegree.getOrDefault(node, 0) + 1);
            }
        }

        // bfs
        // PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> indegree.get(x)));
        //
        // while (!queue.isEmpty()) {
        //     int node = queue.poll();
        //
        //
        // }

        return res;
    }

    private void dfs(int node, int level) {
    }
}
