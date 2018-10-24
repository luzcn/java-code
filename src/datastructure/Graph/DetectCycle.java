package datastructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetectCycle {

  private Map<Integer, ArrayList<Integer>> graph = new HashMap<>();


  private boolean hasCycle(int u, boolean[] visited, Set<Integer> ancestor) {
    if (ancestor.contains(u)) {
      return true;
    }

    if (visited[u]) {
      return false;
    }

    ancestor.add(u);
    visited[u] = true;
    for (int v : graph.getOrDefault(u, new ArrayList<>())) {
      if (hasCycle(v, visited, ancestor)) {
        return true;
      }
    }
    ancestor.remove(u);

    return false;
  }


  public List<List<Integer>> cycles = new ArrayList<>();

  private void getAllCycles(int u, int parent, boolean[] visited, List<Integer> path) {
    if (visited[u]) {
      this.cycles.add(new ArrayList<>(path));
      return;
    }

    visited[u] = true;
    path.add(u);

    for (int v : graph.getOrDefault(u, new ArrayList<>())) {
      if (v == parent) {
        continue;
      }

      getAllCycles(v, u, visited, path);
    }

    path.remove(path.size() - 1);
    visited[u] = false;
  }

  // detect if an directed graph has cycle
  public boolean detectCycle(int n, int[][] edges) {
    // directed graph
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
    }

    boolean[] visited = new boolean[n];

    for (int i = 0; i < n; i++) {
      if (!visited[i] && hasCycle(i, visited, new HashSet<>())) {
        return true;
      }
    }
    return false;

  }
}
