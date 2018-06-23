package leetcode;

import java.util.*;

// We are playing the Guess Game. The game is as follows:
//
// I pick a number from 1 to n. You have to guess which number I picked.
//
// Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
//
// However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
//
// Example:
//
// n = 10, I pick 8.
//
// First round:  You guess 5, I tell you that it's higher. You pay $5.
// Second round: You guess 7, I tell you that it's higher. You pay $7.
// Third round:  You guess 9, I tell you that it's lower. You pay $9.
//
// Game over. 8 is the number I picked.
//
// You end up paying $5 + $7 + $9 = $21.
// Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
public class GuessNumberHigherOrLower_375 {

    // the brute force idea is we consider each number in the range [1...n] as pivot
    // the cost would be pivot + Math.max(cost(pivot-1), cost(pivot+1));
    private int calculate(int n, int low, int high) {
        if (low >= high) {
            return 0;
        }

        int minRes = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            int cost = i + Math.max(calculate(n, low, i - 1), calculate(n, i + 1, high));
            minRes = Math.min(minRes, cost);
        }

        return minRes;
    }

    public int getMoneyAmount(int n) {

        return calculate(n, 1, n);
    }

    // dp idea,
    // let dp[i,j] indicate the max cost of number range [i..j]
    // - dp[k,k] = 0,
    // - dp[i,j] =
    private int calculateDP(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            // (i,j) range is (start, n - len +1)
            for (int start = 1; start < n - len + 1; start++) {

                int minRes = Integer.MAX_VALUE;
                for (int pivot = start; pivot < start + len - 1; pivot++) {
                    int cost = Math.max(dp[start][pivot - 1], dp[pivot + 1][start + len - 1]);
                    minRes = Math.min(minRes, cost);
                }

                dp[start][start + len - 1] = minRes;
            }
        }

        return dp[1][n];
    }
}
