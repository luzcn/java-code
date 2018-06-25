package leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;
        int min = 1;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {

            int currentMax = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            int currentMin = Math.min(nums[i], Math.min(max * nums[i], min * nums[i]));

            res = Math.max(res, currentMax);

            max = currentMax;
            min = currentMin;
        }

        return res;
    }
}
