package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to
 * bottom, column by column).
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

  private SortedMap<Integer, List<Integer>> map = new TreeMap<>();

  private List<List<Integer>> bfs(TreeNode root) {

    if (root == null) {
      return new ArrayList<>();
    }

    // bfs needs a queue
    List<Data> queue = new ArrayList<>();
    List<Data> temp = new ArrayList<>();

    queue.add(new Data(0, root));

    while (!queue.isEmpty()) {

      for (Data p : queue) {
        TreeNode current = p.node;
        int key = p.key;

        map.computeIfAbsent(key, k -> new ArrayList<>()).add(current.val);

        if (current.left != null) {
          temp.add(new Data(key - 1, current.left));
        }

        if (current.right != null) {
          temp.add(new Data(key + 1, current.right));
        }
      }

      queue = temp;
      temp = new ArrayList<>();
    }

    return new ArrayList<>(map.values());
  }


  public List<List<Integer>> verticalOrder(TreeNode root) {

    return bfs(root);

  }

  private class Data {

    int key;
    TreeNode node;

    Data(int key, TreeNode node) {
      this.key = key;
      this.node = node;
    }
  }

}
