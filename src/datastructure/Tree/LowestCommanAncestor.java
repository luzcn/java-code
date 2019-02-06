package datastructure.Tree;

import leetcode.TreeNode;

// Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
//
// According to the definition of LCA on Wikipedia:
// “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants
// (where we allow a node to be a descendant of itself).”
//
public class LowestCommanAncestor {

  // Bottom-up recursive solution
  private TreeNode LCA(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null) {
      return null;
    }

    if (node == p || node == q) {
      return node;
    }

    TreeNode leftNode = LCA(node.left, p, q);
    TreeNode rightNode = LCA(node.right, p, q);

    if (leftNode != null && rightNode != null) {
      return node;
    } else if (leftNode != null) {
      return leftNode;
    } else {
      return rightNode;
    }
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    // some edge case
    if (p == q) {
      return p;
    }

    if (p == null || q == null) {
      return null;
    }
    return LCA(root, p, q);
  }


  // find Lowest Common Ancestor from Binary Search Tree
  // the regular solution takes O(n) time
  // we should use the BST property,
  // when we see the first node where node.val <= p.val && node.val >= q.val (or vice versa), the node is the LCA
  private TreeNode LCAonBST(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }

    if (root.val > p.val && root.val > q.val) {
      return LCAonBST(root.left, p, q);
    } else if (root.val < p.val && root.val < q.val) {
      return LCAonBST(root.right, p, q);
    } else {
      return root;
    }
  }
}
