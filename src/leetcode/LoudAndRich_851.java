package leetcode;

// In a group of N people (labelled 0, 1, 2, ..., N-1),
// each person has different amounts of money, and different levels of quietness.
//
// For convenience, we'll call the person with label x, simply "person x".
//
// We'll say that richer[i] = [x, y] if person x definitely has more money than person y.
// Note that richer may only be a subset of valid observations.
//
// Also, we'll say quiet[x] = q if person x has quietness q.
//
// Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]),
// among all people who definitely have equal to or more money than person x.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoudAndRich_851 {

  // Graph problem
  // dfs
  public int[] loudAndRich(int[][] richer, int[] quiet) {

    int n = quiet.length;
    int[] res = new int[n];
    Arrays.fill(res, -1);

    // build the graph
    // graph[x] = list of richer persons
    for (int[] row : richer) {
      graph.computeIfAbsent(row[1], k -> new ArrayList<>()).add(row[0]);
    }

    for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    for (int i = 0; i < n; i++) {
      dfs(quiet, i, res);
    }
    return res;
  }

  private HashMap<Integer, List<Integer>> graph = new HashMap<>();

  private int dfs(int[] quiet, int person, int[] res) {

    if (res[person] != -1) {
      return res[person];
    }

    res[person] = person;
    for (int neighbor : graph.getOrDefault(person, new ArrayList<>())) {

      int y = dfs(quiet, neighbor, res);

      if (quiet[y] < quiet[res[person]]) {
        res[person] = y;
      }
    }

    return res[person];
  }
}
