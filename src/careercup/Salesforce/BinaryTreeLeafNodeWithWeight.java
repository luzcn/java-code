package careercup.Salesforce;

import leetcode.TreeNode;

// Given a binary tree, check if there is a leaf node with specific weight
//
// Note:
// - the weight of a tree node is the node.value * the level of this node,
//  the root node has level 1
public class BinaryTreeLeafNodeWithWeight {

  public boolean findLeafNode(TreeNode root, int targetWeight) {

    return dfs(root, 1, targetWeight);
  }

  private boolean dfs(TreeNode node, int level, int target) {
    if (node == null) {
      return false;
    }

    if (node.left == null && node.right == null) {
      return level * node.val == target;
    }

    return dfs(node.left, level + 1, target) || dfs(node.right, level + 1, target);

  }
}
