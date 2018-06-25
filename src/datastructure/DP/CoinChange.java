package datastructure.DP;

import java.util.*;

// You are given coins of different denominations and a total amount of money amount.
//
// Write a function to compute the fewest number of coins that you need to make up that amount.
// If that amount of money cannot be made up by any combination of the coins, return -1.
//
// Example 1:
//
// Input: coins = [1, 2, 5], amount = 11
// Output: 3
// Explanation: 11 = 5 + 5 + 1
// Example 2:
//
// Input: coins = [2], amount = 3
// Output: -1
public class CoinChange {

    public int coinChange(int[] coins, int amount) {

        return dfs(coins, 0, amount);
    }

    private int dfs(int[] coins, int pos, int amount) {

        // find a solution
        if (amount == 0) {
            return 0;
        }

        // no more coins can use
        if (pos >= coins.length && amount > 0) {
            return Integer.MAX_VALUE - 1;
        }

        // too many coins used
        if (amount < 0) {
            return Integer.MAX_VALUE - 1;
        }

        return Math.min(dfs(coins, pos, amount - coins[pos]) + 1, dfs(coins, pos + 1, amount));
    }

    // O(m*n) time, O(m*n) space
    private int makeChangeDP(int[] coins, int amount) {
        int m = amount;
        int n = coins.length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = Integer.MAX_VALUE - 1;
        }

        // dp(i,j) indicate the min cost of i money and coins[0..j] coins.
        // dp[i][j] = min(dp[i-coins[j-1]] + 1, dp[i][j-1]); if i > coins[j-1]
        // else dp[i][j] = dp[i][j-1], cannot use this coin.

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (i > coins[j - 1]) {
                    // current amount > coin value
                    dp[i][j] = Math.min(dp[i - coins[j - 1]][j] + 1, dp[i][j - 1]);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[m][n] == Integer.MAX_VALUE - 1 ? -1 : dp[m][n];
    }

    // O(m) space
    private int makChangeDP2(int[] coins, int amount) {
        int m = amount;
        int n = coins.length;
        int[] dp = new int[m + 1];
        
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[m] == Integer.MAX_VALUE - 1 ? -1 : dp[m];
    }
}
