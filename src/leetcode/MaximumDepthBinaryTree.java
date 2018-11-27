package leetcode;

import java.util.List;

// Given a binary tree, find its maximum depth.
//
// The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Given binary tree [3,9,20,null,null,15,7],
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its depth = 3.
public class MaximumDepthBinaryTree {

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftSub = maxDepth(root.left);
    int rightSub = maxDepth(root.right);

    return Math.max(leftSub, rightSub) + 1;
  }


  // Maximum depth of N-array tree
  public int maxDepthNTree(NTreeNode root) {

    if (root == null) {
      return 0;
    }

    int res = 0;
    for (NTreeNode child : root.children) {
      res = Math.max(res, maxDepthNTree(child));
    }

    return res + 1;
  }

  private class NTreeNode {

    int val;
    List<NTreeNode> children;
  }
}
