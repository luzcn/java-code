package datastructure.Graph;

import java.util.*;

import leetcode.DataStreamAsDisjointIntervals.Interval;

public class ShortestPath {


    // Assume the edge weight is 1 here
    // get the minimum distance sum from "start" to other nodes
    public int shortestPathTotalSum(int[][] edges, int start) {
        //

        // construct undirected graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        // from the "start" node, bfs, find the shortest distance to each other nodes.
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> candidates = new LinkedList<>();
        Queue<Integer> temp = new LinkedList<>();
        int totalDistance = 0;
        int level = 1;

        candidates.add(start);
        while (!candidates.isEmpty()) {

            // if we have edge [0,1],[0,2],[1,2],[1,3],[2,3], to find the shortest distance from 0 to 3
            // when visited 0, it will add 1 and 2 into the next level candidate.
            // but the edge [1,2] is not useful here,
            // because we already calculated the shortest distance from 0->1 and 0->2
            // and obviously, the path 0->1->2->3 cannot be the shortest
            //
            //     0
            //    / \
            //   1 - 2
            //    \  /
            //     3
            visited.addAll(candidates);
            int current = candidates.poll();

            for (int child : graph.getOrDefault(current, new ArrayList<>())) {

                if (visited.contains(child)) {
                    continue;
                }

                temp.add(child);
                totalDistance += level;
            }

            if (candidates.isEmpty()) {
                level++;
                candidates = temp;
                temp = new LinkedList<>();
            }
        }
        return totalDistance;
    }


    //  Dijkstra algorithm to compute the shortest distance from source to each vertex.
    //  Dijkstra’s algorithm doesn’t work for graphs with negative weight edges.
    //  For graphs with negative weight edges, Bellman–Ford algorithm can be used.
    //
    //  O(n^2)
    //  http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
    public int[] shortestPath(int[][] graph, int start) {

        // input graph is adjacent matrix
        int n = graph.length;

        // check if the node is visited
        boolean[] visited = new boolean[n];

        // the dp array to save the distance from start to i so far.
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for (int count = 0; count < n; count++) {

            int u = findMin(distance, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {

                // the node is not visited
                // there is one edge [u,v]
                // distance[u] + weight of edge [u,v] is less than current distance[v]
                if (!visited[v] && graph[u][v] > 0 && distance[u] != Integer.MAX_VALUE
                        && distance[u] + graph[u][v] < distance[v]) {
                    // need to update the distance of v node

                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
        return distance;

    }

    // find the index of node which has minimum distance
    private int findMin(int[] distance, boolean[] visited) {
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < minValue) {
                minIndex = i;
                minValue = distance[i];
            }
        }

        return minIndex;
    }
}
