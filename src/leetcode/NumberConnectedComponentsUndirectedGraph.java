package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of
 * nodes), write a function to find the number of connected components in an undirected graph.
 *
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
 */
public class NumberConnectedComponentsUndirectedGraph {

  private void dfs(Map<Integer, List<Integer>> graph, int node, boolean[] visited) {
    if (visited[node]) {
      return;
    }

    visited[node] = true;

    if (!graph.containsKey(node)) {
      return;
    }

    for (int n : graph.get(node)) {
      dfs(graph, n, visited);
    }
  }

  public int countComponents(int n, int[][] edges) {
    // use a hashmap to describe the undirected-graph
    // key is node, value is list of connected nodes

    Map<Integer, List<Integer>> graph = new HashMap<>();
    boolean[] visited = new boolean[n];

    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(edge[1]);
      graph.computeIfAbsent(edge[1], v -> new ArrayList<>()).add(edge[0]);
    }

    int count = 0;
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        continue;
      }

      count++;
      dfs(graph, i, visited);
    }
    return count;
  }
}
