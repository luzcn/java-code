package leetcode;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the
 * root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the
 * height of the tree.
 */
public class BinarySearchTreeIterator {

  private TreeNode currentNode;
  private Stack<TreeNode> stack;

  public BinarySearchTreeIterator(TreeNode root) {
    this.currentNode = root;
    stack = new Stack<>();

    while (currentNode != null) {
      stack.add(currentNode);
      currentNode = currentNode.left;
    }

  }

  /**
   * @return whether we have a next smallest number
   */
  public boolean hasNext() {

    return currentNode != null;
  }

  /**
   * @return the next smallest number
   */
  public int next() {
    // morris tree idea
    if (currentNode == null) {
      return 0;
    }

    while (currentNode.left != null) {
      TreeNode prev = currentNode.left;
      while (prev.right != null && prev.right != currentNode) {
        prev = prev.right;
      }

      if (prev.right == null) {
        prev.right = currentNode;
        currentNode = currentNode.left;
      } else {
        prev.right = null;
        break;
      }
    }

    int value = currentNode.val;
    currentNode = currentNode.right;

    return value;

  }


  private int stackSolution() {
    if (stack.isEmpty()) {
      return 0;
    }

    TreeNode current = stack.pop();
    int value = current.val;

    current = current.right;
    while (current != null) {
      stack.add(current);
      current = current.left;
    }

    return value;
  }
}
