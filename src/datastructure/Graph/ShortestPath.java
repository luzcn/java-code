package datastructure.Graph;

import java.util.*;

public class ShortestPath {
    //  Dijkstra algorithm to compute the shortest distance from source to each vertex.
    //  Dijkstra’s algorithm doesn’t work for graphs with negative weight edges.
    //  For graphs with negative weight edges, Bellman–Ford algorithm can be used.
    //
    //  O(n^2)
    //  http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/

    // Assume the edge weight is 1 here
    public int shortestPath(int[][] edges, int start) {
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
}
