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

        int product = nums[0];
        int currentMax = nums[0], currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int prevMax = currentMax;
            int prevMin = currentMin;

            currentMax = Math.max(nums[i], Math.max(prevMax * nums[i], prevMin * nums[i]));
            currentMin = Math.min(nums[i], Math.min(prevMax * nums[i], prevMin * nums[i]));

            product = Math.max(product, currentMax);
        }

        return product;
    }
}
