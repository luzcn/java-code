package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Given a binary search tree (BST) with duplicates,
// find all the mode(s) (the most frequently occurred element) in the given BST.
//
// Assume a BST is defined as follows:
//
// - The left subtree of a node contains only nodes with keys less than or equal to the node's key.
// - The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
// - Both the left and right subtrees must also be binary search trees.
///
public class FindModeInBST {

  private TreeNode prev = null;
  private List<Integer> modes = new ArrayList<>();
  private int count = 1;
  private int maxCount = 1;

  private void inorder(TreeNode node) {
    if (node == null) {
      return;
    }

    inorder(node.left);

    if (prev != null) {
      if (prev.val == node.val) {
        count++;
      } else {
        count = 1;
      }
    }

    if (count > maxCount) {
      maxCount = count;
      this.modes.clear();
      this.modes.add(node.val);
    } else if (count == maxCount) {
      this.modes.add(node.val);
    }

    prev = node;
    inorder(node.right);
  }


  // Use stack, O(n) space
  private void findIterative(TreeNode root) {
    if (root == null) {
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    while (true) {
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else if (!stack.isEmpty()) {
        current = stack.pop();

        if (prev != null) {
          if (prev.val == current.val) {
            count++;
          } else {
            count = 1;
          }
        }

        if (count > maxCount) {
          maxCount = count;
          modes.clear();
          modes.add(current.val);
        } else if (count == maxCount) {
          modes.add(current.val);
        }

        prev = current;
        current = current.right;
      } else {
        break;
      }
    }
  }

  // Use Morris tree traverse
  // O(1) space
  private void findMorrisTree(TreeNode root) {
    TreeNode current = root;
    TreeNode pre = null;

    while (current != null) {

      if (current.left == null) {
        // print out current value
        current = current.right;
      } else {
        pre = current.left;

        while (pre.right != null && pre.right != current) {
          pre = pre.right;
        }

        if (pre.right == null) {
          pre.right = current;

          current = current.left;
        } else {
          pre.right = null;

          if (current.val == pre.val) {
            count++;
          } else {
            count = 1;
          }

          if (count > maxCount) {
            maxCount = count;
            this.modes.clear();
            this.modes.add(current.val);
          } else if (count == maxCount) {
            this.modes.add(current.val);
          }

          // print out current value
          current = current.right;
        }
      }
    }
  }

  public int[] findMode(TreeNode root) {
    findIterative(root);

    return this.modes.stream().mapToInt(i -> i).toArray();
  }
}
