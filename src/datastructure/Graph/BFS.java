package datastructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {

  Map<Integer, List<Integer>> graph = new HashMap<>();

  public void traverse(int n, int[][] edges, int start) {
    // directed graph
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
    }

    boolean[] visited = new boolean[n];

    // bfs, use queue to save visited node
    Queue<Integer> queue = new LinkedList<>();

    // start from 0
    queue.add(start);

    while (!queue.isEmpty()) {
      int node = queue.poll();

      if (visited[node]) {
        continue;
      }

      visited[node] = true;
      System.out.println(node);
      if (graph.containsKey(node)) {
        queue.addAll(graph.get(node));
      }
    }
  }
}
