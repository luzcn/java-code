package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// There are a total of n courses you have to take, labeled from 0 to n - 1.
//
// Some courses may have prerequisites, for example to take course 0 you have to first take course
// 1, which is expressed as a pair: [0,1]
//
// Given the total number of courses and a list of prerequisite pairs, is it possible for you to
// finish all courses?
//
// For example:
//
// 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished
// course 0. So it is possible.
//
// 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have
// finished course 0, and to take course 0 you should also have finished course 1. So it is
// impossible.
//
// Thoughts: 1. we can use directed graph to represent the course requirements 2. detect if there is
// cycle in graph
///

public class CourseSchedule_207 {

  private HashMap<Integer, List<Integer>> graph;

  // dfs, detect if there is a cycle.
  private boolean hasCycle(int node, Set<Integer> ancestors,
      boolean[] visited) {
    if (ancestors.contains(node)) {
      return false;
    }

    if (visited[node]) {
      return true;
    }

    visited[node] = true;
    ancestors.add(node);

    for (int next : graph.getOrDefault(node, new ArrayList<>())) {
      // find cycle, return true, do not need to find other nodes.
      if (hasCycle(next, ancestors, visited)) {
        return true;
      }
    }
    ancestors.remove(node);

    return false;
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {

    // construct the graph
    graph = new HashMap<>();
    for (int[] edge : prerequisites) {
      graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
    }

    // used to track visited nodes in dfs
    boolean[] visited = new boolean[numCourses];

    // use to track if there is cycle in directed graph
    Set<Integer> ancestors = new HashSet<>();

    for (int i = 0; i < numCourses; i++) {
      if (visited[i]) {
        continue;
      }

      if (hasCycle(i, ancestors, visited)) {
        return false;
      }
    }

    return true;
  }


  // bfs solution
  // 1. construct the directed graph
  // 2. use array degree[] to indicate the in-degree is 0 node
  private boolean canFinishBFS(int numCourses, int[][] prerequisites) {

    int[] degree = new int[numCourses];

    // construct the graph
    graph = new HashMap<>();
    for (int[] edge : prerequisites) {
      graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);

      degree[edge[0]]++;
    }

    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < numCourses; i++) {
      if (degree[i] == 0) {
        queue.addLast(i);
      }
    }

    int count = 0;
    while (!queue.isEmpty()) {

      int current = queue.getFirst();
      queue.removeFirst();
      count++;

      for (int next : graph.getOrDefault(current, new ArrayList<>())) {
        degree[next]--;

        if (degree[next] == 0) {
          queue.addLast(next);
        }
      }
    }

    return count == numCourses;
  }
}
