package leetcode;

// Given a binary tree, determine if it is height-balanced.
//
// For this problem, a height-balanced binary tree is defined as:
//
// a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
// Example 1:
//
// Given the following tree [3,9,20,null,null,15,7]:
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
// Return true.
//
// Example 2:
//
// Given the following tree [1,2,2,3,3,null,null,4,4]:
//
//        1
//       / \
//      2   2
//     / \
//    3   3
//   / \
//  4   4
// Return false.
public class BalancedBinaryTree_110 {

  private boolean balance = true;

  // bottom-up recursive
  // O(n) time
  // O(logn) space
  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftSub = dfs(node.left);
    int rightSub = dfs(node.right);

    // the depth of the two subtrees of every node never differ by more than 1
    if (Math.abs(leftSub - rightSub) > 1) {
      this.balance = false;
    }

    return Math.max(leftSub, rightSub) + 1;
  }

  public boolean isBalanced(TreeNode root) {
    dfs(root);
    return this.balance;
  }
}
