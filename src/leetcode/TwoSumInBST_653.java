package leetcode;

/**
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the
 * BST such that their sum is equal to the given target.
 */

//Example 1:
// Input:
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7
//
// Target = 9
//
// Output: True
public class TwoSumInBST_653 {

  private TreeNode root;

  private TreeNode binarySearch(TreeNode node, int t) {
    if (node == null) {
      return null;
    }

    if (node.val == t) {
      return node;
    }

    if (node.val < t) {
      return binarySearch(node.right, t);
    } else {
      return binarySearch(node.left, t);
    }
  }


  // for every node in BST, if we found targetNode is not null and not the node itself
  // we can return true. O(nlogn) time complexity
  private boolean dfs(TreeNode node, int t) {
    if (node == null) {
      return false;
    }

    TreeNode targetNode = binarySearch(root, t - node.val);
    if (targetNode != null && targetNode != node) {
      return true;
    }
    return dfs(node.left, t) || dfs(node.right, t);
  }

  public boolean findTarget(TreeNode root, int k) {
    this.root = root;

    return dfs(root, k);
  }
}
