package leetcode;

// Given a tree, rearrange the tree in in-order
//
// so that the leftmost node in the tree is now the root of the tree,
// and every node has no left child and only 1 right child.

// similar to convert bst to double linked list 426
public class IncreasingOrderSearchTree_897 {

  public TreeNode increasingBST(TreeNode root) {

    TreeNode newRoot = root;

    while (newRoot.left != null) {
      newRoot = newRoot.left;
    }

    dfs(root);
    return newRoot;
  }

  private TreeNode prev = null;

  private void dfs(TreeNode node) {
    if (node == null) {
      return;
    }

    dfs(node.left);

    if (prev != null) {
      prev.right = node;
      node.left = null;
    }

    prev = node;
    dfs(node.right);
  }
}
