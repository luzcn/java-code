package leetcode;

import java.util.HashMap;

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible
 * combinations that add up to a positive integer target.
 *
 * Example:
 *
 * nums = [1, 2, 3] target = 4
 *
 * The possible combination ways are: (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3,
 * 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7. Follow up: What if negative numbers are allowed in the given array?
 * How does it change the problem? What limitation we need to add to the question to allow negative
 * numbers?
 */
public class CombinationSum4 {


  // recursive + memoization
  private int dfs(int[] nums, int target, HashMap<Integer, Integer> memo) {
    if (target < 0) {
      return 0;
    }

    if (target == 0) {
      return 1;
    }

    if (memo.get(target) != null) {
      return memo.get(target);
    }

    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      res += dfs(nums, target - nums[i], memo);
    }

    memo.put(target, res);
    return res;
  }

  public int combinationSum4(int[] nums, int target) {
    if (nums.length == 0) {
      return 0;
    }

    // dp solution
    int n = nums.length;

    int[] dp = new int[target + 1];

    dp[0] = 1;

    for (int j = 1; j <= target; j++) {
      for (int i = 0; i < n; i++) {
        if (j >= nums[i]) {
          dp[j] += dp[j - nums[i]];
        }
      }
    }

    return dp[target];
  }
}
