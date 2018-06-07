package leetcode;

import java.util.*;

// For a undirected graph with tree characteristics, we can choose any node as the root.
// The result graph is then a rooted tree.
//
// Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
// Given such a graph, write a function to find all the MHTs and return a list of their root labels.
//
// Format
// The graph contains n nodes which are labeled from 0 to n - 1.
// You will be given the number n and a list of undirected edges (each edge is a pair of labels).
//
// You can assume that no duplicate edges will appear in edges.
// Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//
// Example 1 :
//
// Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//
//         0
//         |
//         1
//        / \
//       2   3
//
// Output: [1]
public class MinimumHeightTree {

    // bfs the graph, build the tree
    // from observation, we can see
    // 1. the node with 1 out-degree (leaf node) cannot be the root of MHT.
    // 2. the final result can only have 1 or 2 root nodes

    // we do not need to construct the tree and calculate the height,
    // we can keep removing the leaf nodes from the graph, until the graph nodes <= 2
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // build the graph
        // find the leaf nodes (nodes have 1 neighbor)
        // remove them from the graph, keep iterating until graph nodes <= 2

        if (n <= 1) {
            return Arrays.asList(0);
        }

        // build graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            // undirected graph
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        // find the leaf nodes
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (graph.size() > 2) {
            // n -= leaves.size();

            List<Integer> newLeaves = new ArrayList<>();

            for (int leaf : leaves) {
                // remove leaf node

                int parent = graph.get(leaf).get(0);    // leaf node has only 1 neighbor

                // in java, ArrayList<Integer>.remove(int i) is removing the index
                // need to manually convert to Integer Object
                graph.get(parent).remove(Integer.valueOf(leaf));
                graph.remove(leaf);

                // after removing leaf node, the parent node may be the new leaf node
                if (graph.get(parent).size() == 1) {
                    newLeaves.add(parent);
                }
            }

            leaves = newLeaves;
        }

        return leaves;
    }
}
