package leetcode;

/**
 * Find the sum of all left leaves in a given binary tree.
 */

//Example:
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
//
// There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
public class SumOfLeftLeaves {

  private int sum = 0;

  private void dfs(TreeNode node, TreeNode parent) {
    if (node == null) {
      return;
    }

    // leaf node
    if (node.left == null && node.right == null) {
      // left leaf
      if (parent != null && parent.left == node) {
        this.sum += node.val;
      }
    }

    dfs(node.left, node);
    dfs(node.right, node);
  }

  public int sumOfLeftLeaves(TreeNode root) {
    dfs(root, null);

    return this.sum;
  }
}
