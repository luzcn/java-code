package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class CloneGraph {


    private void dfs(UndirectedGraphNode node,
            Map<UndirectedGraphNode, UndirectedGraphNode> map,
            Set<UndirectedGraphNode> visited) {

        if (node == null || visited.contains(node)) {
            return;
        }

        visited.add(node);

        if (!map.containsKey(node)) {
            map.put(node, new UndirectedGraphNode(node.label));
        }

        for (UndirectedGraphNode n : node.neighbors) {
            if (!map.containsKey(n)) {
                // the copy node is not created yet,
                // need to create and save in the hash map
                map.put(n, new UndirectedGraphNode(n.label));
            }

            map.get(node).neighbors.add(map.get(n));

            dfs(n, map, visited);
        }

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

    ;
}
