package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// Given a binary tree, determine if it is a valid binary search tree (BST).
//
// Assume a BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
public class ValidateBinarySearchTree_98 {

  public boolean isValidBST(TreeNode root) {
    return isValidRec(root);
  }


  // in-order recursive solution
  private TreeNode prev = null;

  private boolean isValidRec(TreeNode node) {

    if (node == null) {
      return true;
    }

    boolean leftSub = isValidRec(node.left);
    if (prev != null && prev.val >= node.val) {
      return false;
    }

    prev = node;

    boolean rightSub = isValidRec(node.right);

    return leftSub && rightSub;
  }

  // in-order iterative solution
  private boolean isValid(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque<>();

    TreeNode current = root;
    TreeNode prev = null;
    while (true) {
      if (current != null) {
        stack.addLast(current);
        current = current.left;
      } else if (!stack.isEmpty()) {
        current = stack.removeLast();

        if (prev != null && prev.val >= current.val) {
          return false;
        }
        prev = current;
        current = current.right;

      } else {
        break;
      }
    }

    return true;
  }
}
