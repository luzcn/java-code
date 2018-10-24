package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling
 * only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 */
//Example:
//
// root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//       10
//      /  \
//     5   -3
//    / \    \
//   3   2   11
//  / \   \
// 3  -2   1
//
// Return 3. The paths that sum to 8 are:
//
// 1.  5 -> 3
// 2.  5 -> 2 -> 1
// 3. -3 -> 11
public class PathSum3 {

  private int[] nums = new int[1 << 10];  // 1024
  private int count;
  private List<List<Integer>> result = new ArrayList<>();

  private void addArrayToList(int i, int j) {
    List<Integer> path = new ArrayList<>();
    while (i >= j) {
      path.add(nums[i]);
      i--;
    }

    this.result.add(path);
  }

  private void dfs(TreeNode node, int sum, int i) {
    if (node == null) {
      return;
    }

    nums[i] = node.val;
    int pathSum = 0;
    for (int j = i; j >= 0; j--) {
      pathSum += nums[j];
      if (pathSum == sum) {
        this.count++;
        addArrayToList(i, j);
      }
    }

    dfs(node.left, sum, i + 1);
    dfs(node.right, sum, i + 1);
  }

  public int pathSum(TreeNode root, int sum) {
    dfs(root, sum, 0);

    result.forEach(System.out::println);
    return this.count;
  }
}
