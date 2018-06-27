package careercup.Google;

import java.util.*;

// 每张卡片都有一个值，给定一堆卡片从一头拿，每次可以拿一到三张，两人轮流拿，求最高得分。考虑卡片值为负的情况。
public class CardPlayGame {

    // DP
    public int getMaxSum(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        // assume takes from the right side
        // dp[i] = max( sum(i, n-1) - dp[i+k]) for 1 <= k <= 3

        dp[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {

            int sum = getSum(nums, i, n - 1);

            for (int k = 1; k <= 3; k++) {
                if (i + k < n) {
                    dp[i] = Math.max(dp[i], sum - dp[i + k]);
                }
            }
        }

        return Math.max(dp[0], getSum(nums, 0, n - 1) - dp[0]);
    }

    private int getSum(int[] nums, int i, int j) {
        int sum = 0;
        while (i <= j) {
            sum += nums[i++];
        }

        return sum;
    }
}
