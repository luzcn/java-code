package leetcode;

import java.util.*;

import com.sun.source.tree.Tree;

// Given a binary tree where every node has a unique value, and a target key k,
// find the value of the nearest leaf node to target k in the tree.
//
// Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree.
// Also, a node is called a leaf if it has no children.
//
// In the following examples, the input tree is represented in flattened form row by row.
// The actual root tree given will be a TreeNode object.
//
// Example 1:
//
// Input:
// root = [1, 3, 2], k = 1
// Diagram of binary tree:
//           1
//          / \
//         3   2
//
// Output: 2 (or 3)
//
// Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
// Example 2:
//
// Input:
// root = [1], k = 1
// Output: 1
//
// Explanation: The nearest leaf node is the root node itself.
// Example 3:
//
// Input:
// root = [1,2,3,4,null,null,null,5,null,6], k = 2
// Diagram of binary tree:
//              1
//             / \
//            2   3
//           /
//          4
//         /
//        5
//       /
//      6
//
// Output: 3
// Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
public class ClosestLeafInBinaryTree_742 {

  public int findClosestLeaf(TreeNode root, int k) {
    if (root == null) {
      return 0;
    }

    getNodeK(root, k, 0);

    return res;
  }

  private int pathToK = 0;
  private TreeNode nodeK = null;
  private HashMap<Integer, Integer> map = new HashMap<>();
  private int minPath = Integer.MAX_VALUE;
  private int res = 0;

  // top-down recursive
  private void getNodeK(TreeNode node, int k, int level) {
    if (node == null) {
      return;
    }

    if (node.val == k) {
      pathToK = level;
      nodeK = node;
      return;
    }

    getNodeK(node.left, k, level + 1);
    getNodeK(node.right, k, level + 1);
  }

  private int[] dfs(TreeNode node) {

    return null;
  }

}