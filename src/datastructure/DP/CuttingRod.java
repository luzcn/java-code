package datastructure.DP;

import java.util.*;

// a rod has a total length
// cut the rod into different length will have different values
// try to cut the rod and the maximum values.
public class CuttingRod {

    public int cutRod(int[] prices) {
        return dfs(prices, prices.length - 1);
    }


    private int dfs(int[] prices, int n) {
        if (n == 0) {
            return 0;
        }

        int best = 0;
        for (int i = 0; i < prices.length; i++) {

            int value = prices[i];
            int leftOPT = dfs(prices, n - (i + 1));

            best = Math.max(best, value + leftOPT);
        }

        return best;
    }


    private int cutRodDP(int[] prices, int n) {
        if (n <= 0) {
            return 0;
        }

        // dp[i] indicate the max value at i-th position
        // dp[i] = max (prices[i], dp[j] + d[i-j]) for all 0<= j < i
        int[] dp = new int[n];
        dp[0] = prices[0];

        for (int i = 1; i < n; i++) {

            // if no cut, dp[i] is the price[i]
            dp[i] = prices[i];
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }

        return dp[n - 1];
    }
}
