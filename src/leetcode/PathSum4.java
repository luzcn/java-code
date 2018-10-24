package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of
 * three-digits integers.
 *
 * For each integer in this list: 1. The hundreds digit represents the depth D of this node, 1 <= D
 * <= 4.
 *
 * 2. The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8.
 * The position is the same as that in a full binary tree.
 *
 * 3. The units digit represents the value V of this node, 0 <= V <= 9. Given a list of ascending
 * three-digits integers representing a binary with the depth smaller than 5. You need to return the
 * sum of all paths from the root towards the leaves.
 */

//Example 1:
// Input: [113, 215, 221]
// Output: 12
// Explanation:
// The tree that the list represents is:
//     3
//    / \
//   5   1
//
// The path sum is (3 + 5) + (3 + 1) = 12.
//
// Example 2:
// Input: [113, 221]
// Output: 4
// Explanation:
// The tree that the list represents is:
//     3
//      \
//       1
//
// The path sum is (3 + 1) = 4.
public class PathSum4 {

  private int getFirst(int num) {
    return num / 100;
  }

  private int getMid(int num) {
    return (num / 10) % 10;
  }

  private int getLast(int num) {
    return num % 10;
  }

  private Map<Integer, Integer> map = new HashMap<>();
  private int totalSum = 0;


  private void dfs(int node, int sum) {
    if (!map.containsKey(node)) {
      return;
    }

    sum += map.get(node);

    int depth = node / 10, pos = node % 10;
    int left = (depth + 1) * 10 + 2 * pos - 1;
    int right = left + 1;

    if (!map.containsKey(left) && !map.containsKey(right)) {
      this.totalSum += sum;
      return;
    }

    dfs(left, sum);
    dfs(right, sum);

  }

  public int pathSum(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    // the first 2 numbers are the unique identity of these tree nodes
    // use a hashmap, save the node and value
    for (int n : nums) {
      map.put(n / 10, n % 10);
    }

    dfs(nums[0] / 10, 0);
    return this.totalSum;
  }
}
