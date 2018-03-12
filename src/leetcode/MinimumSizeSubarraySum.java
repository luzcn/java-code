package leetcode;

/**
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s.
 *
 * If there isn't one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubarraySum {

    // two pointer solution, the array has all positive numbers
    public int minSubArrayLen(int s, int[] nums) {

        int begin = 0;
        int end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (end < nums.length) {
            sum += nums[end];

            while (sum >= s) {
                minLength = Math.min(minLength, end - begin + 1);

                sum -= nums[begin++];
            }
            end++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
