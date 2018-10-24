package leetcode;

// Given a binary tree, find the length of the longest path where each node in the path has the same value.
// This path may or may not pass through the root.
//
// Note: The length of path between two nodes is represented by the number of edges between them.
//
// Example 1:
//
// Input:
//
//               5
//              / \
//             4   5
//            / \   \
//           1   1   5
// Output:
//
// 2
// Example 2:
//
// Input:
//
//               1
//              / \
//             4   5
//            / \   \
//           4   4   5
// Output:
//
// 2
public class LongestUnivaluePath {

  private int ans = 0;

  public int longestUnivaluePath(TreeNode root) {

    dfs(root);
    return ans;
  }

  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }

    if (node.left == null && node.right == null) {
      return 0;
    }

    int leftSub = dfs(node.left);
    int rightSub = dfs(node.right);

    int leftPath = 0;
    int rightPath = 0;
    if (node.left != null && node.left.val == node.val) {
      leftPath = leftSub + 1;
    }
    if (node.right != null && node.right.val == node.val) {
      rightPath = rightSub + 1;
    }

    ans = Math.max(ans, leftPath + rightPath);
    return Math.max(leftPath, rightPath);

  }
}
