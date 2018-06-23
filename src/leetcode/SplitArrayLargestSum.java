package leetcode;

import java.util.*;

// Given an array which consists of non-negative integers and an integer m,
// you can split the array into m non-empty continuous subarrays.
//
// Write an algorithm to minimize the largest sum among these m subarrays.
//
// Note:
// If n is the length of array, assume the following constraints are satisfied:
//
// 1 ≤ n ≤ 1000
// 1 ≤ m ≤ min(50, n)
// Examples:
//
// Input:
// nums = [7,2,5,10,8]
// m = 2
//
// Output:
// 18
//
// Explanation:
// There are four ways to split nums into two subarrays.
// The best way is to split it into [7,2,5] and [10,8],
// where the largest sum among the two subarrays is only 18.
public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int m) {
        if (m == 1) {
            return getSum(nums, 0, nums.length - 1);
        }

        int[][] memo = new int[nums.length + 1][m + 1];

        return dfs(nums, 0, m, memo);
    }

    private int getSum(int[] nums, int i, int j) {
        int sum = 0;
        while (i <= j) {
            sum += nums[i++];
        }

        return sum;
    }

    // top-down dfs with memoization
    private int dfs(int[] nums, int index, int m, int[][] memo) {
        if (memo[index][m] > 0) {
            return memo[index][m];
        }

        if (m == 1) {
            return getSum(nums, index, nums.length - 1);
        }

        int best = Integer.MAX_VALUE;
        for (int i = index; i < nums.length - 1; i++) {
            int sum = getSum(nums, index, i);
            int leftOPT = dfs(nums, i + 1, m - 1, memo);

            best = Math.min(best, Math.max(sum, leftOPT));
        }

        memo[index][m] = best;
        return best;
    }

    // O(n^2*m) time, O(n*m) space
    private int splitArrayDP(int[] nums, int m) {

        int n = nums.length;

        // get the total range sum
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        // define dp[i][j] to be the minimum largest subarray sum for splitting nums[0..i] into j parts.
        // dp[i][j] = min(dp[i][j], max(dp[k][j-1], sum(i,k)) for each 0<= k < i
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sums[i] - sums[k]));
                }
            }
        }

        return dp[n][m];
    }
}
