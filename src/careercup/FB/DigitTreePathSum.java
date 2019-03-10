package careercup.FB;

import leetcode.TreeNode;

// https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=488885&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
// Digit Trie Path Sum
//       2
//      / \
//     3   4
//   /  \
// 1    5
// The output should be: 231 + 235 + 24 = 490
public class DigitTreePathSum {

  public int getSum(TreeNode root) {

    dfs(root, 0);

    return sum;
  }

  private int sum = 0;

  private void dfs(TreeNode node, int path) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      sum += path * 10 + node.val;
      return;
    }

    dfs(node.left, path * 10 + node.val);
    dfs(node.right, path * 10 + node.val);
  }
}
