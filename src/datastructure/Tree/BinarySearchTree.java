package datastructure.Tree;

import leetcode.TreeNode;

public class BinarySearchTree {

  public TreeNode insert(TreeNode root, int v) {
    if (root == null) {
      return new TreeNode(v);
    }

    if (v < root.val) {
      root.left = insert(root.left, v);
    } else {
      root.right = insert(root.right, v);
    }

    return root;
  }
}
