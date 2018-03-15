package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private boolean dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> ancestors, boolean[] visited) {
        if (ancestors.contains(node))
            return false;

        if (visited[node])
            return true;

        visited[node] = true;
        ancestors.add(node);
        for (int n : graph.getOrDefault(node, new ArrayList<>())) {
            if (!dfs(graph, n, ancestors, visited)) {
                return false;
            }
        }
        ancestors.remove(node);

        schedule.add(node);
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] result = new int[numCourses];

        for (int[] edge : prerequisites) {
            // the question says [1,0] means 1 depends on 0, need to finish 0 first
            // so the directed graph edge is 0->1
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        boolean[] visited = new boolean[numCourses];
        Set<Integer> ancestors = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (visited[i])
                continue;

            if (!dfs(graph, i, ancestors, visited))
                return result;
        }

        for (int n : this.schedule) {
            result[--numCourses] = n;
        }

        return result;
    }
}
