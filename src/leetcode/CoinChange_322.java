package leetcode;

import java.util.Arrays;

// You are given coins of different denominations and a total amount of money amount.
// Write a function to compute the fewest number of coins that you need to make up that amount.
//
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
// Note:
// You may assume that you have an infinite number of each kind of coin.
public class CoinChange_322 {

  public int coinChange(int[] coins, int amount) {
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
