package leetcode;

import java.util.ArrayList;
import java.util.List;


// Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
// You are asked to burst all the balloons.
//
// If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
// Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
//
// Find the maximum coins you can collect by bursting the balloons wisely.
//
// Note:
//
// You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
// 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
// Example:
// Input: [3,1,5,8]
// Output: 167
// Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
public class BurstBalloons {

  // https://leetcode.com/problems/burst-balloons/discuss/76228/share-some-analysis-and-explanations
  // divide-conquer + memoization solution
  private int maxCoinsRec(int[] nums, int left, int right, int[][] dp) {
    if (left > right) {
      return 0;
    }

    if (dp[left][right] > 0) {
      return dp[left][right];
    }

    int ans = 0;
    for (int i = left; i <= right; i++) {
      //
      // int leftCoins = left - 1 == -1 ? 1 : nums[left - 1];
      // int rightCoins = right + 1 == nums.length ? 1 : nums[right + 1];

      ans = Math.max(ans,
          nums[i] * nums[left - 1] * nums[right + 1] + maxCoinsRec(nums, left, i - 1, dp)
              + maxCoinsRec(nums,
              i + 1, right, dp));
    }

    dp[left][right] = ans;
    return ans;
  }


  // dp[left][right] to indicate the maximum coins we can get from range [i..j] after burst the balloons.
  // dp[left][right] = max(dp[left][right], nums[left - 1]*nums[i]*nums[right + 1] + dp[left][i - 1] + dp[i + 1][right])
  private int maxCoinsDP(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    // pre-process the given array, insert 1 on both begin and end of the array
    List<Integer> numsList = new ArrayList<>();
    numsList.add(1);
    for (int num : nums) {
      numsList.add(num);
    }
    numsList.add(1);
    nums = numsList.stream().mapToInt(x -> x).toArray();
    int n = nums.length;

    int[][] dp = new int[n][n];

    for (int k = 2; k < n; ++k) {
      for (int left = 0; left < n - k; ++left) {
        int right = left + k;
        for (int i = left + 1; i < right; ++i) {
          dp[left][right] = Math
              .max(dp[left][right],
                  nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
        }
      }
    }

    return dp[0][n - 1];

  }

  public int maxCoins(int[] nums) {

    int n = nums.length;
    if (n == 0) {
      return 0;
    }

    List<Integer> numsList = new ArrayList<>();
    numsList.add(1);
    for (int num : nums) {
      numsList.add(num);
    }
    numsList.add(1);

    int[][] dp = new int[n + 2][n + 2];
    return maxCoinsRec(numsList.stream().mapToInt(x -> x).toArray(), 1, n, dp);
  }


}
