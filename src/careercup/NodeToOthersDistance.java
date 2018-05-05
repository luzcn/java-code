package careercup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * design a function such that return the total distance from this node to any other nodes
 * 给的树 是 多枝树 n-ary tree 每个edge distance 为1.
 * 如 Asssume function name as D:   then D(A) = 1+1+1+1+2+2. D(B) = 1+2+2+2+3+3 = 13
 *
 * A
 * ／ |  |  \
 * B      C  D  E
 * |         \
 *
 * F          G
 */
public class NodeToOthersDistance {

    // undirected graph
    private HashMap<MultiTreeNode, List<MultiTreeNode>> graph = new HashMap<>();


    private void dfs(MultiTreeNode node, MultiTreeNode parent) {
        if (node == null) {
            return;
        }

        if (parent != null) {
            graph.computeIfAbsent(parent, k -> new ArrayList<>()).add(node);
            graph.computeIfAbsent(node, k -> new ArrayList<>()).add(parent);
        }

        for (MultiTreeNode n : node.children) {
            dfs(n, node);
        }
    }

    // similar to dijastra's single source shortest path to all vertex
    // the edge weight is 1.
    public int getDistance(MultiTreeNode root, MultiTreeNode start) {

        // dfs construct the graph
        dfs(root, null);

        // from the "start" node, bfs, find the shortest distance to each other nodes.
        Set<MultiTreeNode> visited = new HashSet<>();
        Queue<MultiTreeNode> candidates = new LinkedList<>();
        Queue<MultiTreeNode> temp = new LinkedList<>();
        int totalDistance = 0;
        int level = 1;

        candidates.add(start);
        while (!candidates.isEmpty()) {

            // if we have edge [0,1],[0,2],[1,2]
            // when visited 0, it will add 1 and 2 into the next level candidate.
            // but the edge [1,2] is not useful here,
            // because we already calculated the shortest distance from 0->1 and 0->2
            visited.addAll(candidates);
            MultiTreeNode current = candidates.poll();

            for (MultiTreeNode child : graph.getOrDefault(current, new ArrayList<>())) {

                if (visited.contains(child)) {
                    continue;
                }

                temp.add(child);
                totalDistance += level;
            }

            if (candidates.isEmpty()) {
                level++;
                candidates = temp;
                temp = new LinkedList<>();
            }
        }
        return totalDistance;
    }

    public int getDistanceInGraph(int[][] edges, int start) {
        // construct undirected graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        // from the "start" node, bfs, find the shortest distance to each other nodes.
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> candidates = new LinkedList<>();
        Queue<Integer> temp = new LinkedList<>();
        int totalDistance = 0;
        int level = 1;

        candidates.add(start);
        while (!candidates.isEmpty()) {

            // if we have edge [0,1],[0,2],[1,2],[1,3],[2,3], to find the shortest distance from 0 to 3
            // when visited 0, it will add 1 and 2 into the next level candidate.
            // but the edge [1,2] is not useful here,
            // because we already calculated the shortest distance from 0->1 and 0->2
            // and obviously, the path 0->1->2->3 cannot be the shortest
            //
            //     0
            //    / \
            //   1 - 2
            //    \  /
            //     3
            visited.addAll(candidates);
            int current = candidates.poll();

            for (int child : graph.getOrDefault(current, new ArrayList<>())) {

                if (visited.contains(child)) {
                    continue;
                }

                temp.add(child);
                totalDistance += level;
            }

            if (candidates.isEmpty()) {
                level++;
                candidates = temp;
                temp = new LinkedList<>();
            }
        }
        return totalDistance;
    }

    private class MultiTreeNode {

        int val;
        List<MultiTreeNode> children;

        MultiTreeNode(int v) {
            val = v;
            children = new ArrayList<>();
        }
    }

}
