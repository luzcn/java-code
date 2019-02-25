package leetcode;

import java.util.*;

// Given an array nums of integers, you can perform operations on the array.
//
// In each operation, you pick any nums[i] and delete it to earn nums[i] points.
// After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
//
// You start with 0 points. Return the maximum number of points you can earn by applying such operations.
//
// Example 1:
//
// Input: nums = [3, 4, 2]
// Output: 6
// Explanation:
// Delete 4 to earn 4 points, consequently 3 is also deleted.
// Then, delete 2 to earn 2 points. 6 total points are earned.
//
//
// Example 2:
//
// Input: nums = [2, 2, 3, 3, 3, 4]
// Output: 9
// Explanation:
// Delete 3 to earn 3 points, deleting both 2's and the 4.
// Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
// 9 total points are earned.
//
// Note:
//
// - The length of nums is at most 20000.
// - Each element nums[i] is an integer in the range [1, 10000].
public class DeleteAndEarn_740 {

  public int deleteAndEarn(int[] nums) {

    dfs(nums, 0, 0, new HashMap<>());
    return res;
  }

  private int res = 0;

  // TLE
  private void dfs(int[] nums, int index, int sum, HashMap<Integer, Integer> used) {
    res = Math.max(res, sum);

    // System.out.println(sum);
    if (index >= nums.length) {
      return;
    }

    for (int i = index; i < nums.length; i++) {
      if (used.get(nums[i]) != null && used.get(nums[i]) > 0) {
        continue;
      }

      used.put(nums[i] - 1, used.getOrDefault(nums[i] - 1, 0) + 1);
      used.put(nums[i] + 1, used.getOrDefault(nums[i] + 1, 0) + 1);

      dfs(nums, i + 1, sum + nums[i], used);

      used.put(nums[i] - 1, used.getOrDefault(nums[i] - 1, 0) - 1);
      used.put(nums[i] + 1, used.getOrDefault(nums[i] + 1, 0) - 1);

    }
  }

}
