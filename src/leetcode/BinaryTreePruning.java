package leetcode;

// We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
//
// Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
//
// (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
public class BinaryTreePruning {

  private boolean dfs(TreeNode node) {
    if (node == null) {
      return true;
    }

    boolean leftSub = dfs(node.left);
    boolean rightSub = dfs(node.right);

    if (leftSub) {
      node.left = null;
    }

    if (rightSub) {
      node.right = null;
    }

    return leftSub && rightSub && node.val == 0;
  }

  public TreeNode pruneTree(TreeNode root) {

    if (root == null) {
      return null;
    }

    dfs(root);

    return root;
  }
}
