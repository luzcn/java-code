package datastructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFS {

  private Map<Integer, List<Integer>> graph = new HashMap<>();
  private boolean[] visited;


  private void dfs(int node) {
    if (visited[node]) {
      return;
    }

    System.out.println(node);

    visited[node] = true;
    for (int n : graph.getOrDefault(node, new ArrayList<>())) {
      dfs(n);
    }
  }

  public void traverse(int n, int[][] edges, int start) {
    // directed graph
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
    }

    this.visited = new boolean[n];

    dfs(start);
  }
}
