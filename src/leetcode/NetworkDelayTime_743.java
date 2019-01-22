package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

// There are N network nodes, labelled 1 to N.
//
// Given times, a list of travel times as directed edges times[i] = (u, v, w),
// where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
//
// Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal?
// If it is impossible, return -1.
//
// Note:
// - N will be in the range [1, 100].
// - K will be in the range [1, N].
// - The length of times will be in the range [1, 6000].
// - All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
public class NetworkDelayTime_743 {

  public int networkDelayTime(int[][] times, int N, int K) {

    boolean[] visited = new boolean[N + 1];
    int[] distance = new int[N + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    HashMap<Integer, List<int[]>> graph = new HashMap<>();
    distance[K] = 0;

    // 1-> [2, 4], there is a directed edge from node 1 to 2 and weight is 4
    for (int[] edge : times) {
      graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
    }

    // Dijkstar's single source shortest path, use minHeap
    PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> distance[x]));
    queue.offer(K);

    while (!queue.isEmpty()) {

      int u = queue.poll();

      if (visited[u]) {
        continue;
      }

      visited[u] = true;

      for (int[] neighbor : graph.getOrDefault(u, new ArrayList<>())) {

        int v = neighbor[0];
        int cost = neighbor[1];

        if (distance[u] != Integer.MAX_VALUE && distance[u] + cost < distance[v]) {
          distance[v] = distance[u] + cost;
        }
      }
    }

    int totalCost = 0;
    for (int i = 1; i <= N; i++) {
      if (distance[i] == Integer.MAX_VALUE) {
        return -1;
      }

      totalCost = Math.max(totalCost, distance[i]);
    }

    return totalCost;
  }

}
