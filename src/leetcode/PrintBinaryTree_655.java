package leetcode;

import java.util.*;

// Print a binary tree in an m*n 2D string array following these rules:
//
// - The row number m should be equal to the height of the given binary tree.
// - The column number n should always be an odd number.
// - The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
// The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part).
//
// You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part.
// The left-bottom part and the right-bottom part should have the same size.
//
// Even if one subtree is none while the other is not,
// you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree.
//
// However, if two subtrees are none, then you don't need to leave space for both of them.

// - Each unused space should contain an empty string "".
// - Print the subtrees following the same rules.
// Example 1:
// Input:
//      1
//     /
//    2
// Output:
// [["", "1", ""],
//  ["2", "", ""]]
public class PrintBinaryTree_655 {

  List<List<String>> res = new ArrayList<>();

  public List<List<String>> printTree(TreeNode root) {

    if (root == null) {
      return res;
    }

    // the row size is the height
    // the column size is 2^h - 1

    int depth = getMaxDepth(root);

    int columnSize = (1 << depth) - 1;
    for (int i = 0; i < depth; i++) {
      res.add(new ArrayList<>(Collections.nCopies(columnSize, "")));
    }

    // level order

    dfs(root, 0, columnSize / 2 );
    return res;
  }


  private int getMaxDepth(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftSub = getMaxDepth(node.left);
    int rightSub = getMaxDepth(node.right);

    return Math.max(leftSub, rightSub) + 1;
  }


  private void dfs(TreeNode node, int level, int pos) {
    if (node == null) {
      return;
    }

    res.get(level).set(pos, String.valueOf(node.val));

    dfs(node, level + 1, pos - 1);
    dfs(node, level + 1, pos + 1);
  }
}
