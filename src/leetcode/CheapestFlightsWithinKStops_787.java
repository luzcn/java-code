package leetcode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
//
// Now given all the cities and fights, together with starting city src and the destination dst,
// your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
//
// Example 1:
// Input:
// n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
// src = 0, dst = 2, k = 1
// Output: 200
public class CheapestFlightsWithinKStops_787 {

  // graph problem, bfs
  // Dijkstra's shortest path with priority queue
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

    // build the graph?!
    int[][] graph = new int[n][n];

    for (int[] edge : flights) {
      graph[edge[0]][edge[1]] = edge[2];
    }

    // check if the ndoe is visited,
    // cannot use a boolean array here, because bfs will update the shortest distance,
    // which maybe not useful to reach the destination
    Set<Integer> best = new HashSet();

    // x[0] distance, x[1] node number, x[2] stops
    PriorityQueue<int[]> candidates = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
    candidates.add(new int[]{0, src, 0});

    while (!candidates.isEmpty()) {
      int[] info = candidates.poll();

      int cost = info[0];
      int u = info[1];
      int stops = info[2];

      int key = stops * 1000 + u;
      if (stops > K + 1 || best.contains(key)) {
        continue;
      }

      if (u == dst) {
        return cost;
      }

      best.add(key);
      for (int v = 0; v < n; v++) {
        if (graph[u][v] > 0) {
          candidates.offer(new int[]{cost + graph[u][v], v, stops + 1});
        }
      }
    }
    return -1;
  }

}
