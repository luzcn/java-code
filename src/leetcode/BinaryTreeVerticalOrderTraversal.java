package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples:
 */
// Examples:
//
// Given binary tree [3,9,20,null,null,15,7],
//    3
//   /\
//  /  \
//  9  20
//     /\
//    /  \
//   15   7
// return its vertical order traversal as:
// [
//   [9],
//   [3,15],
//   [20],
//   [7]
// ]
public class BinaryTreeVerticalOrderTraversal {

    // dfs solution
    // each node assign a key, when iterate left sub tree, key - 1
    // right sub tree, key + 1;
    // save the key and list of node values in sorted hash map
    // but guarantee the top down order is a problem
    // and I don't know how to solve it yet.
    private void dfs(TreeNode node, int key) {
        if (node == null) {
            return;
        }

        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(node.val);

        // map.computeIfAbsent(key, k -> new ArrayList<>()).add(node.val);

        dfs(node.left, key - 1);
        dfs(node.right, key + 1);
    }


    private void bfs(TreeNode root) {
        // bfs needs a queue
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

        queue.add(new Pair<>(root, 0));

        while (!queue.isEmpty() && queue.peek() != null) {
            TreeNode current = queue.peek().first;
            int key = queue.peek().second;
            queue.poll();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(current.val);

            if (current.left != null) {
                queue.add(new Pair<>(current.left, key - 1));
            }

            if (current.right != null) {
                queue.add(new Pair<>(current.right, key + 1));
            }
        }
    }

    private SortedMap<Integer, List<Integer>> map = new TreeMap<>();

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        bfs(root);

        for (Map.Entry<Integer, List<Integer>> values : map.entrySet()) {
            result.add(values.getValue());
        }

        return result;
    }

    private class Pair<T, U> {

        T first;
        U second;

        Pair(T f, U s) {
            first = f;
            second = s;
        }
    }
}
