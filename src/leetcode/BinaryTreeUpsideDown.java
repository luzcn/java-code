package leetcode;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node
 * that shares the same parent node) or empty,
 *
 * flip it upside down and turn it into a tree where the original right nodes turned into left leaf
 * nodes.
 *
 * Return the new root.
 *
 * Thought: This question is actually rotating the binary tree in clock wise direction. - the
 * leftmost leaf node is the new root - each right node is either empty or leaf node, we can dfs
 * without checking right node.
 */

//For example:
// Given a binary tree {1,2,3,4,5},
//     1
//    / \
//   2   3
//  / \
// 4   5
// return the root of the binary tree [4,5,2,#,#,3,1].
//    4
//   / \
//  5   2
//     / \
//    3   1
public class BinaryTreeUpsideDown {

  private TreeNode dfs(TreeNode node, TreeNode parent) {
    if (node == null) {
      return null;
    }

    if (node.left == null) {
      return node;
    }

    TreeNode newRoot = dfs(node.left, node);

    if (parent != null) {
      node.left = parent.right;
      node.right = parent;
    } else {
      // node is root node,
      // reset its children to null
      node.left = null;
      node.right = null;
    }

    return newRoot;
  }

  public TreeNode rotateBinaryTree(TreeNode root) {

    return dfs(root, null);
  }
}
