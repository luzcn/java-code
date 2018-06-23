package leetcode;

import java.util.Arrays;

// Given an unsorted array of integers, find the length of longest increasing subsequence.
//
// For example,
// Given [10, 9, 2, 5, 3, 7, 101, 18],
// The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
// Note that there may be more than one LIS combination, it is only necessary for you to return the length.
public class LongestIncreasingSubsequence {

    // DP solution
    // for each i, if we found any j, from [0...i-1], nums[i] >= nums[j] and dp[j] + 1 > dp[i],
    // update the dp[i] = dp[j]+1;
    public int lengthOfLIS(int[] nums) {

        if (nums.length < 2) {
            return nums.length;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }

            result = Math.max(result, dp[i]);
        }

        return result;

    }


    // Given an unsorted array of integers, find the number of longest increasing subsequence.
    //
    // Example 1:
    // Input: [1,3,5,4,7]
    // Output: 2
    // Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
    //
    // Example 2:
    // Input: [2,2,2,2,2]
    // Output: 5
    // Explanation: The length of longest continuous increasing subsequence is 1,
    // and there are 5 subsequences' length is 1, so output 5.
    public int findNumberOfLIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int n = nums.length;

        int[] length = new int[n];
        int[] count = new int[n];

        Arrays.fill(length, 1);
        Arrays.fill(count, 1);

        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {

                    if (length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1;

                        count[i] = count[j];
                    } else if (length[j] + 1 == length[i]) {
                        count[i] += count[j];
                    }
                }
            }

            maxLength = Math.max(maxLength, length[i]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (length[i] == maxLength) {
                result += count[i];
            }
        }
        return result;
    }
}
