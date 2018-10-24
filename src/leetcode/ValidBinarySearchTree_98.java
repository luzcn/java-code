package leetcode;

import java.util.Stack;

// Given a binary tree, determine if it is a valid binary search tree (BST).
// Assume a BST is defined as follows:
// 1. The left subtree of a node contains only nodes with keys less than the node's key.
// 2. The right subtree of a node contains only nodes with keys greater than the node's key.
// 3. Both the left and right subtrees must also be binary search trees.
public class ValidBinarySearchTree_98 {

  public boolean isValidBST(TreeNode root) {

    // use in-order iterative solution

    if (root == null) {
      return true;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    TreeNode prev = null;

    boolean done = false;

    while (!done) {
      if (current != null) {
        stack.push(current);

        current = current.left;
      } else if (!stack.isEmpty()) {
        current = stack.pop();

        if (prev != null && prev.val >= current.val) {
          return false;
        }

        prev = current;
        current = current.right;
      } else {
        done = true;
      }
    }
    return true;
  }

  private TreeNode prev = null;

  // dfs solution
  private boolean inOrder(TreeNode node) {
    if (node == null) {
      return true;
    }

    boolean leftSub = inOrder(node.left);
    if (prev != null && prev.val >= node.val) {
      return false;
    }
    prev = node;

    boolean rightSub = inOrder(node.right);

    return leftSub && rightSub;
  }

  private boolean isBSTRec(TreeNode node, int l, int h) {
    if (node == null) {
      return true;
    }

    return node.val >= l && node.val < h && isBSTRec(node.left, l, node.val) && isBSTRec(node.right,
        node.val, h);
  }
}
