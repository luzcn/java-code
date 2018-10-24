package leetcode;

// Given a binary tree, find the length of the longest consecutive sequence path.
//
// The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
//
// The longest consecutive path need to be from parent to child (cannot be the reverse).
//
// Example 1:
//
// Input:
//
//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5
//
// Output: 3
//
// Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
// Example 2:
//
// Input:
//
//    2
//     \
//      3
//     /
//    2
//   /
//  1
//
// Output: 2
//
// Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
public class BinaryTreeLongestConsecutiveSequence_298 {

  private int res = 0;

  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }

    // if (node.left == null && node.right == null) {
    //     return 1;
    // }

    int leftSub = dfs(node.left);
    int rightSub = dfs(node.right);

    if (node.left != null && node.val + 1 != node.left.val) {
      leftSub = 0;
    }

    if (node.right != null && node.val + 1 != node.right.val) {
      rightSub = 0;
    }

    res = Math.max(res, Math.max(leftSub, rightSub) + 1);

    return Math.max(leftSub, rightSub) + 1;
  }

  public int longestConsecutive(TreeNode root) {
    dfs(root);

    return this.res;
  }
}
