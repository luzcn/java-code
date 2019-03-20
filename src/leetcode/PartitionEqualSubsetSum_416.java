package leetcode;

import java.util.Arrays;

// Given a non-empty array containing only positive integers,
// find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
//
// Note:
// Each of the array element will not exceed 100.
// The array size will not exceed 200.
// Example 1:
//
// Input: [1, 5, 11, 5]
//
// Output: true
//
// Explanation: The array can be partitioned as [1, 5, 5] and [11].
// Example 2:
//
// Input: [1, 2, 3, 5]
//
// Output: false
//
// Explanation: The array cannot be partitioned into equal sum subsets.
public class PartitionEqualSubsetSum_416 {

  // DP idea,
  // p[i,j] to indicate if it can partition at s[j] and sum is i
  // p[i,j] = true if p[i,j - 1] || p[i - s[j], j-1] is true
  // p[0,j] = true
  public boolean canPartition(int[] nums) {

    int n = nums.length;
    int sum = Arrays.stream(nums).sum();

    // the total sum must be even number
    if (sum % 2 != 0) {
      return false;
    }

    int m = sum / 2;

    boolean[][] dp = new boolean[m + 1][n + 1];

    // if sum is 0, we can find two empty set
    // so, p[0,j] is always true
    for (int j = 0; j <= n; j++) {
      dp[0][j] = true;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (i >= nums[j - 1]) {
          dp[i][j] = dp[i][j - 1] || dp[i - nums[j - 1]][j - 1];
        }
      }
    }

    return dp[m][n];
  }
}
