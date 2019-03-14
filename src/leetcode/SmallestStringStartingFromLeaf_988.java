package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z':
// a value of 0 represents 'a', a value of 1 represents 'b', and so on.
//
// Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
//
// Note:
// - any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".
// - A leaf of a node is a node that has no children.
public class SmallestStringStartingFromLeaf_988 {

  public String smallestFromLeaf(TreeNode root) {
    dfs(root, "");

    Collections.sort(res);

    return res.isEmpty() ? "" : res.get(0);
  }


  private List<String> res = new ArrayList<>();

  // top-down recursive
  private void dfs(TreeNode node, String current) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      current += (char) ('a' + node.val);
      res.add((new StringBuilder(current)).reverse().toString());
      return;
    }

    dfs(node.left, current + (char) ('a' + node.val));
    dfs(node.right, current + (char) ('a' + node.val));

  }
}
