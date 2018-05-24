package leetcode;

import java.util.*;

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
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int N, int K) {

        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distance = new HashMap<>();
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        distance.put(K, 0);

        // 1-> [2, 4], there is a directed edge from node 1 to 2 and weight is 4
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        // Dijkstar's single source shortest path, use minHeap
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        queue.offer(new int[]{K, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int u = current[0];

            if (visited.contains(u)) {
                continue;
            }

            visited.add(u);

            for (int[] neighbor : graph.getOrDefault(u, new ArrayList<>())) {

                int v = neighbor[0];
                int dis = neighbor[1];

                if (!visited.contains(v) && (distance.get(v) == null || distance.get(u) + dis < distance.get(v))) {
                    distance.put(v, distance.get(u) + dis);

                    queue.offer(new int[]{v, distance.get(v)});
                }
            }
        }

        if (distance.size() != N) {
            return -1;
        }

        return distance.values().stream().mapToInt(x -> x).max().getAsInt();
    }

}
