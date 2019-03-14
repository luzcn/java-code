package leetcode;

// Return the root node of a binary search tree that matches the given preorder traversal.
//
// Note:
// - a binary search tree is a binary tree where for every node,
//   any descendant of node.left has a value < node.val,
//   and any descendant of node.right has a value > node.val.
//
// - a preorder traversal displays the value of the node first,
//   then traverses node.left, then traverses node.right.

public class ConstructBSTFromPreorder_1008 {

  public TreeNode bstFromPreorder(int[] preorder) {
    return dfs(preorder, 0, preorder.length - 1);
  }


  // top down recursive
  private TreeNode dfs(int[] preorder, int l, int r) {
    if (l > r) {
      return null;
    }

    // the first value is always the root node from pre-order
    // all the values that are smaller than the current value on its right side are the left-sub tree
    // we process it recursively.

    int value = preorder[l];
    int i = l + 1;
    while (i <= r && preorder[i] < value) {
      i++;
    }

    TreeNode root = new TreeNode(value);
    root.left = dfs(preorder, l + 1, i - 1);
    root.right = dfs(preorder, i, r);

    return root;
  }
}
