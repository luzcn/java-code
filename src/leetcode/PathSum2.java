package leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * Path Sum 2 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum
 * equals the given sum.
 *
 * https://leetcode.com/problems/path-sum-ii/description/
 */
//
// For example:
// Given the below binary tree and sum = 22,
//               5
//              / \
//             4   8
//            /   / \
//           11  13  4
//          /  \    / \
//         7    2  5   1
// return
// [
//    [5,4,11,2],
//    [5,8,4,5]
// ]

public class PathSum2 {

  private List<List<Integer>> result = new ArrayList<>();
  private List<Integer> current = new ArrayList<>();

  private void dfs(TreeNode node, int pathSum, int sum) {
    if (node == null) {
      return;
    }

    // leaf node
    if (node.left == null && node.right == null) {
      if (pathSum + node.val == sum) {
        current.add(node.val);
        this.result.add(new ArrayList<>(current));
        current.remove(current.size() - 1);
      }
      return;
    }

    current.add(node.val);
    dfs(node.left, pathSum + node.val, sum);
    dfs(node.right, pathSum + node.val, sum);
    current.remove(current.size() - 1);
  }

  public List<List<Integer>> pathSum(TreeNode root, int sum) {

    dfs(root, 0, sum);
    return this.result;
  }
}
