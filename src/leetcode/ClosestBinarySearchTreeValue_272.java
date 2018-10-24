package leetcode;

import java.util.LinkedList;
import java.util.List;

// Closest Binary Search Tree Value II
// Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
// Note:
//
// - Given target value is a floating point.
// - You may assume k is always valid, that is: k ≤ total nodes.
// - You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
// Example:
//
// Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
//
//     4
//    / \
//   2   5
//  / \
// 1   3
//
// Output: [4,3]

public class ClosestBinarySearchTreeValue_272 {

  // sliding window idea
  //
  // in-order traverse in BST and keep a window "res"
  // if res.size < k, add the node value
  // else if comparing node.val - t and res.getFirst() - t, if less, remove the res.first and add node.val to it.
  // because the elements in this window are sorted in ascending order, only need to compare first and node

  private LinkedList<Integer> res = new LinkedList<>();

  private void inorder(TreeNode node, double t, int k) {
    if (node == null) {
      return;
    }

    if (res.size() < k) {
      res.addLast(node.val);
    } else if (Math.abs((double) node.val - t) < Math.abs((double) res.getFirst() - t)) {
      res.removeFirst();
      res.addLast(node.val);
    }

    if (t > (double) node.val) {
      inorder(node.right, t, k);
    } else {
      inorder(node.left, t, k);
    }
  }

  public List<Integer> closestKValues(TreeNode root, double target, int k) {

    inorder(root, target, k);
    return res;
  }
}
