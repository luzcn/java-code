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
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Thoughts:
 * 1. we can use directed graph to represent the course requirements
 * 2. detect if there is cycle in graph
 */

public class CourseSchedule {

// dfs, detect if there is a cycle.
private boolean dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> ancestors, boolean[] visited) {
    if (ancestors.contains(node)) {
        return false;
    }

    if (visited[node]) {
        return true;
    }

    visited[node] = true;
    ancestors.add(node);

    for (int n : graph.getOrDefault(node, new ArrayList<>())) {
        // find cycle, return false, do not need to find other nodes.
        if (!dfs(graph, n, ancestors, visited))
            return false;
    }
    ancestors.remove(node);

    return true;
}

public boolean canFinish(int numCourses, int[][] prerequisites) {

    // construct the graph
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int[] edge : prerequisites) {
        graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
    }

    // used to track visited nodes in dfs
    boolean[] visited = new boolean[numCourses];

    // use to track if there is cycle in directed graph
    Set<Integer> ancestors = new HashSet<>();

    for (int i = 0; i < numCourses; i++) {
        if (visited[i])
            continue;

        if (!dfs(graph, i, ancestors, visited))
            return false;
    }

    return true;
}
}
