package leetcode;

// A binary tree is univalued if every node in the tree has the same value.
//
// Return true if and only if the given tree is univalued.
public class UnivaluedBinaryTree_965 {

  public boolean isUnivalTree(TreeNode root) {

    if (root == null) {
      return true;
    }

    boolean leftSub = isUnivalTree(root.left);

    if (prev != null && prev.val != root.val) {
      return false;
    }
    prev = root;

    boolean rightSub = isUnivalTree(root.right);

    return leftSub && rightSub;
  }

  private TreeNode prev = null;
}
