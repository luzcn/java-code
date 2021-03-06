package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
// reconstruct the itinerary in order.

// All of the tickets belong to a man who departs from JFK.
// Thus, the itinerary must begin with JFK.
//
// Note:
// If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
// when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//
// All airports are represented by three capital letters (IATA code).
// You may assume all tickets form at least one valid itinerary.
// Example 1:
// tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
// Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
//
// Example 2:
// tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
// Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
public class ReconstructItinerary {

  private List<String> result = new ArrayList<>();
  private HashMap<String, List<String>> graph = new HashMap<>();

  // dfs, topo sort
  // but we cannot use a "visited" hashset to indicate if the next node is visited.
  // we should check if the node has more destination "next" to reach and each dfs iteration need to remove this "next"
  private void dfs(String node) {

    while (graph.get(node) != null && !graph.get(node).isEmpty()) {
      String next = graph.get(node).get(0);

      graph.get(node).remove(0);

      dfs(next);
    }
    this.result.add(node);

  }

  public List<String> findItinerary(String[][] tickets) {

    // directed graph
    for (String[] t : tickets) {
      graph.computeIfAbsent(t[0], k -> new ArrayList<>()).add(t[1]);
    }

    // sort the list
    for (List<String> values : graph.values()) {
      values.sort(String::compareTo);
    }

    dfs("JFK");

    // topo sort, reverse the result
    Collections.reverse(result);
    return result;
  }
}
