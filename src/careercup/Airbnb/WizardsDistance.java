package careercup.Airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

// There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard.
// Define the cost between wizards and wizard as square of different of i and j.
//
// To find the min cost between 0 and 9.

public class WizardsDistance {

  // Dijkstra's single source shortest distance

  private HashMap<Integer, List<Integer>> graph = new HashMap<>();
  private static final int N = 10;

  public int minCost(int[][] relations) {

    // build graph
    for (int i = 0; i < N; i++) {
      int[] wizards = relations[i];

      for (int w : wizards) {
        graph.computeIfAbsent(i, k -> new ArrayList<>()).add(w);
      }
    }

    HashMap<Integer, Integer> path = new HashMap<>();
    boolean[] visited = new boolean[10];
    int[] distance = new int[10];
    Arrays.fill(distance, Integer.MAX_VALUE);

    // bfs
    distance[0] = 0;
    PriorityQueue<Integer> candidates = new PriorityQueue<>(
        Comparator.comparingInt(x -> distance[x]));
    candidates.add(0);

    while (!candidates.isEmpty()) {
      int node = candidates.poll();

      visited[node] = true;

      for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        if (!visited[neighbor]) {
          if (distance[node] + getCost(node, neighbor) < distance[neighbor]) {
            distance[neighbor] = distance[node] + getCost(node, neighbor);

            candidates.add(neighbor);
            path.put(neighbor, node);
          }
        }
      }

    }

    return distance[9];
  }

  private int getCost(int i, int j) {
    return (j - i) * (j - i);
  }
}
