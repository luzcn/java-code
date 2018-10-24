package leetcode;

// Given a Binary Search Tree (BST) with root node "root", and a target value "V",
// split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value,
//
// while the other subtree has all nodes that are greater than the target value.
//
// It's not necessarily the case that the tree contains a node with value V.
//
// Additionally, most of the structure of the original tree should remain.
//
// Formally, for any child C with parent P in the original tree,
// if they are both in the same subtree after the split, then node C should still have the parent P.
// You should output the root TreeNode of both subtrees after splitting, in any order.

public class SplitBST {

  // dfs solution
  // the left sub tree only needs to update right children
  // the right sub tree only needs to update left children.
  public TreeNode[] splitBST(TreeNode root, int V) {

    if (root == null) {
      return new TreeNode[]{null, null};
    }

    if (root.val > V) {
      TreeNode[] result = splitBST(root.left, V);
      root.left = result[1];

      return new TreeNode[]{result[0], root};
    } else {
      TreeNode[] result = splitBST(root.right, V);
      root.right = result[0];

      return new TreeNode[]{root, result[1]};
    }
  }


}
