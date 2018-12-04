package leetcode;

// Given a binary tree with n nodes,
//
// your task is to check if it's possible to partition the tree to two trees
// which have the equal sum of values after removing exactly one edge on the original tree.
//
// Example 1:
// Input:
//     5
//    / \
//   10 10
//     /  \
//    2   3
//
// Output: True
// Explanation:
//     5
//    /
//   10
//
// Sum: 15
//
//    10
//   /  \
//  2    3
//
// Sum: 15
// Example 2:
// Input:
//     1
//    / \
//   2  10
//     /  \
//    2   20
//
// Output: False
// Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
public class EqualTreePartition_663 {

  // O(n) time but scan twice
  // O(n) space for dfs call stack
  public boolean checkEqualTree(TreeNode root) {

    getTotal(root);

    if (sum % 2 != 0) {
      return false;
    }

    dfs(root);
    return canSplit;
  }

  private boolean canSplit = false;
  private int sum = 0;

  private void getTotal(TreeNode node) {
    if (node == null) {
      return;
    }

    getTotal(node.left);
    sum += node.val;
    getTotal(node.right);
  }

  private Integer dfs(TreeNode node) {

    // use integer here, because if the total sum is 0
    // the null child should NOT be split
    if (node == null) {
      return null;
    }

    Integer leftSub = dfs(node.left);
    Integer rightSub = dfs(node.right);

    // comparing before return the value, because if the total sum is 0, the entire tree should NOT be split
    if ((leftSub != null && leftSub == sum / 2) || (rightSub != null && rightSub == sum / 2)) {
      canSplit = true;
    }

    return (leftSub == null ? 0 : leftSub) + (rightSub == null ? 0 : rightSub) + node.val;
  }

}
