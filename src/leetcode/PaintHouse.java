package leetcode;


import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 *
 * Find the minimum cost to paint all houses.
 */
public class PaintHouse {

    // n is the house number
    // k is the previous house color
    // so, we need to call this dfs function 3 times and count the minimum
    // O(2^n) time complexity
    private int dfs(int[][] costs, int n, int k) {
        if (n == 0) {
            return costs[0][k];
        }

        if (k == 0) {
            return Math.min(dfs(costs, n - 1, 1), dfs(costs, n - 1, 2));
        }


        if (k == 1) {
            return Math.min(dfs(costs, n - 1, 0), dfs(costs, n - 1, 2));
        }

        return Math.min(dfs(costs, n - 1, 0), dfs(costs, n - 1, 1));
    }


    // DP solution
    // let p(i, k) indicates the ith house painting the kth color
    // p(i, k) = cost[i, k] +  minimum of p(i-1,0), p(i-1, 1), p(i-1, 2), and the i-1 color is not k,
    // otherwise use the second smallest cost
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }

        int n = costs.length;
        int[] dp = {costs[0][0], costs[0][1], costs[0][2]};


        for (int i = 1; i < n; i++) {

            // compute the minimum cost of ith house in each color
            int cost0 = Math.min(dp[1], dp[2]) + costs[i][0];
            int cost1 = Math.min(dp[0], dp[2]) + costs[i][1];
            int cost2 = Math.min(dp[0], dp[1]) + costs[i][2];

            // update the dp
            dp[0] = cost0;
            dp[1] = cost1;
            dp[2] = cost2;
        }

        return Arrays.stream(dp).min().getAsInt();
    }

    // DP solution
    // similar to paint house 1 problem, find the min and second-min cost of previous house cost
    // let dp[i,j] to indicate the cost of ith house with jth color
    // dp[i,j] = min of i-1th house cost if the color is not equivalent to j, or second-min of i-1 house + the cost[i,j];
    //
    // since we only check with dp[i-1], we can simplify to use a single array to save space.
    public int minCostKColor(int[][] costs) {

        if (costs.length == 0)
            return 0;

        int n = costs.length;
        int k = costs[0].length;


        int[] dp = new int[k];
        for (int i = 0; i < k; i++) {
            dp[i] = costs[0][i];
        }


        for (int i = 1; i < n; i++) {

            // find the min and second-min of previous house cost
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;

            for (int v : dp) {
                if (v < min1) {
                    min2 = min1;
                    min1 = v;
                } else if (v < min2) {
                    min2 = v;
                }
            }

            for (int j = 0; j < k; j++) {
                if (dp[j] == min1) {
                    dp[j] = min2 + costs[i][j];
                } else {
                    dp[j] = min1 + costs[i][j];
                }
            }
        }

        return Arrays.stream(dp).min().getAsInt();
    }
}
