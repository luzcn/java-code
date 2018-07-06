package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So the correct course order is [0,1]
 *
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
 * Another correct ordering is[0,2,1,3].
 *
 * Thougths:
 * use topological sort to find the dependency in DAG
 * - topo sort is similar to dfs, bottom up recursive and save in a stack
 * - topo sort only works in directed graph without cycle
 */
public class CourseSchedule2 {

    private List<Integer> schedule = new ArrayList<>();
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private boolean[] visited;
    private boolean[] ancestors;

    private boolean dfs(int node) {
        if (ancestors[node]) {
            return false;
        }

        if (visited[node]) {
            return true;
        }

        visited[node] = true;
        ancestors[node] = true;
        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            if (!dfs(next)) {
                return false;
            }
        }
        ancestors[node] = false;
        schedule.add(node);

        return true;
    }


    private int[] bfs(int numCourses, int[][] prerequisites) {
        // build graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        List<Integer> result = new ArrayList<>();

        for (int[] edge : prerequisites) {
            // the question says [1,0] means 1 depends on 0, need to finish 0 first
            // so the directed graph edge is 1->0 and the bottom up
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);

            // update the indegree
            inDegree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {

            // find all the node with 0 in-degree
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child : graph.getOrDefault(node, new ArrayList<>())) {
                inDegree[child]--;

                if (inDegree[child] == 0) {
                    queue.add(child);
                }
            }
            result.add(node);
        }

        Collections.reverse(result);
        if (result.size() != numCourses) {
            return new int[0];
        }

        return result.stream().mapToInt(x -> x).toArray();
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for (int[] edge : prerequisites) {
            // the question says [1,0] means 1 depends on 0, need to finish 0 first
            // so the directed graph edge is 1->0 and the bottom up
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        visited = new boolean[numCourses];
        ancestors = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i]) {
                continue;
            }

            if (!dfs(i)) {
                return new int[0];
            }
        }

        return this.schedule.stream().mapToInt(x -> x).toArray();
    }
}
