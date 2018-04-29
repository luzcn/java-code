package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k,
 * write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k,
 * that is, sums up to n*k where n is also an integer.
 *
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 */
public class ContinuousSubarraySum {

    private boolean checkDP(int[] nums, int k) {
        // DP idea, use an auxiliary array to save the accumulate sum of [0...i]
        // O(n^2)
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int sum = dp[j] - dp[i] + nums[i];

                if (sum == k || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {

        if (k < 0 || nums.length < 2) {
            return false;
        }

        // use a hash map to save the accumulated sum of [0...i] and the index i
        // if we found sum - n*k in the hash map, consider the subarray [map.get(sum - n*k)... i] is a valid
        // but the problem is how can we know this n?
        // we can save sum % k

        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;

        // if k is 0, sum%k is not valid
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (k != 0) {
                sum = sum % k;
            }

            if (map.containsKey(sum)) {

                if (i - map.get(sum) > 1) {
                    // requires at least 2 elements
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}
