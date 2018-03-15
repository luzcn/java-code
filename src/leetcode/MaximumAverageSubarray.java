package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaximumAverageSubarray {

    /**
     * Given an array consisting of n integers, find the contiguous subarray of given length k
     * that has the maximum average value.
     *
     * And you need to output the maximum average value.
     *
     * Example 1:
     * Input: [1,12,-5,-6,50,3], k = 4
     * Output: 12.75
     * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
     * Note:
     * 1 <= k <= n <= 30,000.
     * Elements of the given array will be in the range [-10,000, 10,000].
     */
    public double findMaxAverage(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            map.put(i, sum);
        }

        // the first k elements sum
        double maxSubarraySum = map.get(k - 1);
        for (int i = k; i < nums.length; i++) {
            maxSubarraySum = Math.max(maxSubarraySum, map.get(i) - map.get(i - k));
        }

        return maxSubarraySum / k;
    }


    /**
     * Given an array consisting of n integers,
     * find the contiguous subarray whose length is greater than or equal to k that has the maximum average value.
     *
     * And you need to output the maximum average value.
     *
     * Example 1:
     * Input: [1,12,-5,-6,50,3], k = 4
     * Output: 12.75
     *
     * Explanation:
     * when length is 5, maximum average value is 10.8,
     * when length is 6, maximum average value is 9.16667.
     * Thus return 12.75.
     * Note:
     * 1 <= k <= n <= 10,000.
     * Elements of the given array will be in range [-10,000, 10,000].
     * The answer with the calculation error less than 10-5 will be accepted.
     */
    public double findMaxAverage2(int[] nums, int k) {
        return 0;
    }
}
