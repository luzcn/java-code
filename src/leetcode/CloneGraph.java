package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CloneGraph {


  private void dfs(UndirectedGraphNode node,
      Map<UndirectedGraphNode, UndirectedGraphNode> map,
      Set<UndirectedGraphNode> visited) {

    if (node == null || visited.contains(node)) {
      return;
    }

    visited.add(node);
    map.putIfAbsent(node, new UndirectedGraphNode(node.label));

    for (UndirectedGraphNode n : node.neighbors) {
      // the copy node is not created yet,
      // need to create and save in the hash map
      map.putIfAbsent(n, new UndirectedGraphNode(n.label));

      map.get(node).neighbors.add(map.get(n));

      dfs(n, map, visited);
    }

  }


  private UndirectedGraphNode bfs(UndirectedGraphNode node) {
    if (node == null) {
      return null;
    }

    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    // bfs
    Deque<UndirectedGraphNode> queue = new ArrayDeque<>();
    HashSet<UndirectedGraphNode> visited = new HashSet<>();

    queue.add(node);

    while (!queue.isEmpty()) {

      UndirectedGraphNode current = queue.getFirst();
      queue.removeFirst();

      if (visited.contains(current)) {
        continue;
      }

      visited.add(current);

      if (map.get(current) == null) {
        map.put(current, new UndirectedGraphNode(current.label));
      }

      for (UndirectedGraphNode next : current.neighbors) {

        if (map.get(next) == null) {
          map.put(next, new UndirectedGraphNode(next.label));
        }

        map.get(current).neighbors.add(map.get(next));

        if (!visited.contains(next)) {
          queue.add(next);
        }
      }
    }

    return map.get(node);
  }


  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    Set<UndirectedGraphNode> visited = new HashSet<>();

    dfs(node, map, visited);
    return map.get(node);
  }


  private class UndirectedGraphNode {

    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<UndirectedGraphNode>();
    }
  }
}
