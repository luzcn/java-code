package leetcode;

import java.util.*;

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
public class CheapestFlightsWithinKStops {

    // graph problem, bfs
    // Dijkstra's shortest path
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        // build the graph?!
        int[][] graph = new int[n][n];

        for (int[] edge : flights) {
            graph[edge[0]][edge[1]] = edge[2];
        }

        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;

        for (int count = 0; count < K; count++) {
            int u = findMin(distance, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] > 0 && distance[u] != Integer.MAX_VALUE
                        && distance[u] + graph[u][v] < distance[v]) {
                    // update distance[v]
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }


    private int findMin(int[] dist, boolean[] visited) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < minValue) {
                minValue = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}
