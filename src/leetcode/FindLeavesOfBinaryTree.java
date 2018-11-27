package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all
// leaves,
//
// repeat until the tree is empty.
//
// Example:
// Given binary tree
//           1
//          / \
//         2   3
//        / \
//       4   5
// Returns [4, 5, 3], [2], [1].
public class FindLeavesOfBinaryTree {

  private List<List<Integer>> res = new ArrayList<>();

  private boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }


  private TreeNode dfs(TreeNode node, List<Integer> current) {
    if (node == null) {
      return null;
    }

    if (isLeaf(node)) {

      current.add(node.val);
      return null;
    }

    node.left = dfs(node.left, current);
    node.right = dfs(node.right, current);

    return node;
  }

  public List<List<Integer>> findLeaves(TreeNode root) {

    if (root == null) {
      return res;
    }

    while (!isLeaf(root)) {
      List<Integer> current = new ArrayList<>();

      dfs(root, current);

      res.add(current);
    }

    res.add(Arrays.asList(root.val));

    // res.forEach(System.out::println);

    return res;
  }
}
