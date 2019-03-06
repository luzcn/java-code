package leetcode;

import java.util.HashMap;

// Given a binary search tree and a node in it, find the in-order successor of that node in the
// BST.
//
// The successor of a node p is the node with the smallest key greater than p.val.
///
public class InorderSuccessorInBST_285 {

  // if the gieven node p has right child, then the left-most node in its right sub tree is the successor
  // otherwise, if p is the left child of its parent, then this parent is the successor
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

    if (root == null || p == null) {
      return null;
    }

    // if p has right child, find the left-most node in the right sub tree
    if (p.right != null) {
      TreeNode successor = p.right;
      while (successor.left != null) {
        successor = successor.left;
      }

      return successor;
    }

    // the successor is the parent of p, if p is the left child of its parent.
    // build the parent path;
    getParent(root, null, p);

    TreeNode x = p;
    TreeNode y = map.get(x);

    while (y != null && y.right == x) {
      x = y;
      y = map.get(x);
    }

    return y;
  }

  // a hashmap of child-parent mapping
  private HashMap<TreeNode, TreeNode> map = new HashMap<>();

  private void getParent(TreeNode node, TreeNode parent, TreeNode target) {
    if (node == null) {
      return;
    }

    map.put(node, parent);
    if (node == target) {
      return;
    }

    if (node.val < target.val) {
      getParent(node.right, node, target);
    } else {
      getParent(node.left, node, target);
    }
  }


  // dfs solution
  private TreeNode prev = null;
  private TreeNode succ = null;

  private void inorder(TreeNode node, TreeNode p) {
    if (node == null) {
      return;
    }

    inorder(node.left, p);
    if (prev == p) {
      succ = node;
    }

    prev = node;
    inorder(node.right, p);
  }

  // or use iterative
  private TreeNode successor(TreeNode root, TreeNode p) {
    TreeNode succ = null;

    while (root != null) {
      if (root.val > p.val) {
        succ = root;
        root = root.left;
      } else {
        root = root.right;
      }
    }

    return succ;
  }
}
