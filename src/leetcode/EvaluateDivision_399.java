package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// Equations are given in the format A / B = k,
// where A and B are variables represented as strings, and k is a real number (floating point number).
//
// Given some queries, return the answers. If the answer does not exist, return -1.0.
//
// Example:
// Given a / b = 2.0, b / c = 3.0.
// queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
// return [6.0, 0.5, -1.0, 1.0, -1.0 ].
//
// The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
// where equations.size() == values.size(), and the values are positive.
//
// This represents the equations. Return vector<double>.
//
// According to the example above:
//
// equations = [ ["a", "b"], ["b", "c"] ],
// values = [2.0, 3.0],
// queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
//
// The input is always valid.
// You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
public class EvaluateDivision_399 {
  // thought:
  // this actually a graph problem
  // we maintain a list
  // a => [b, 2.0], [d,4.0]....[x,0.2]..
  // b => [c, 3.0], [ff,0.5]...
  /// ....
  // we can see this is a graph adjacent list, and a/b=2.0 => b/a=0.5, we also need to add the b->a edge
  // for the query equation a/e, we dfs to find if there is a path from a -> e, if yes, we get all the values production

  private HashMap<String, List<Data>> graph = new HashMap<>();

  // private boolean dfs(String node, String end, HashSet<String> visited, double value, int index) {
  //     if (visited.contains(node)) {
  //         return false;
  //     }
  //
  //     if (node.equals(end)) {
  //
  //         result[index] = value;
  //         return true;
  //     }
  //
  //     visited.add(node);
  //
  //     for (Data child : graph.getOrDefault(node, new ArrayList<>())) {
  //
  //         if (dfs(child.id, end, visited, value * child.value, index)) {
  //             return true;
  //         }
  //     }
  //
  //     return false;
  // }


  private double dfs(String node, String end, HashSet<String> visited) {
    // x / x should return -1, because x is not int he graph
    if (graph.get(node) == null || graph.get(end) == null || visited.contains(node)) {
      return -1.0;
    }

    if (node.equals(end)) {
      return 1.0;
    }

    visited.add(node);
    for (Data child : graph.getOrDefault(node, new ArrayList<>())) {
      double res = dfs(child.id, end, visited);

      if (res != -1.0) {
        return res * child.value;
      }
    }

    return -1.0;
  }

  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

    double[] result = new double[queries.length];

    if (equations.length != values.length) {
      return result;
    }

    // build the graph
    for (int i = 0; i < equations.length; i++) {
      String[] eq = equations[i];
      double value = values[i];

      // a/b = 2.0
      graph.computeIfAbsent(eq[0], key -> new ArrayList<>()).add(new Data(eq[1], value));
      // so we have b/a = 1/2.0
      graph.computeIfAbsent(eq[1], key -> new ArrayList<>()).add(new Data(eq[0], 1 / value));
    }

    // dfs for every query,
    for (int i = 0; i < queries.length; i++) {
      HashSet<String> visited = new HashSet<>();

      String start = queries[i][0];
      String end = queries[i][1];

      // if (start.equals(end) && !graph.containsKey(start)) {
      //
      //     // x/x should return -1, because x is not in the graph
      //     result[i] = -1.0;
      //     continue;
      // }

      result[i] = dfs(start, end, visited);
    }

    return result;
  }


  private class Data {

    String id;
    double value;

    Data(String name, double val) {
      id = name;
      value = val;
    }
  }
}
