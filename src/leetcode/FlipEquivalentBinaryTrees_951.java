package leetcode;

import java.util.*;

// For a binary tree T, we can define a flip operation as follows:
// - choose any node, and swap the left and right child subtrees.
//
// A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
//
// Write a function that determines whether two binary trees are flip equivalent.
// The trees are given by root nodes root1 and root2.
//
//
public class FlipEquivalentBinaryTrees_951 {
  // similar to mirror-tree, symmetric tree
  public boolean flipEquiv(TreeNode root1, TreeNode root2) {

    if (root1 == null) {
      return root2 == null;
    }

    if (root2 == null) {
      return root1 == null;
    }

    return (root1.val == root2.val) && (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
        || flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right));
  }
}
