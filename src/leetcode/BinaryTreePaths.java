package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given a binary tree, return all root-to-leaf paths.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Input:
//
//    1
//  /   \
// 2     3
//  \
//   5
//
// Output: ["1->2->5", "1->3"]
//
// Explanation: All root-to-leaf paths are: 1->2->5, 1->3
public class BinaryTreePaths {

  private List<String> res = new ArrayList<>();

  private void dfs(TreeNode node, String path) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {

      res.add(path + node.val);
      return;
    }

    dfs(node.left, path + node.val + "->");
    dfs(node.right, path + node.val + "->");

  }

  public List<String> binaryTreePaths(TreeNode root) {
    dfs(root, "");

    return res;
  }
}
