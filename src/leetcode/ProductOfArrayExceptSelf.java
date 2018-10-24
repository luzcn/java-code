package leetcode;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is
 * equal to the product of all the elements of nums except nums[i].
 *
 * Solve it without division and in O(n).
 *
 * For example, given [1,2,3,4], return [24,12,8,6].
 */
public class ProductOfArrayExceptSelf {

  // DP idea, scan from left to right and right to left
  // compute the previous product
  public int[] productExceptSelf(int[] nums) {

    if (nums.length == 0) {
      return new int[0];
    }

    int n = nums.length;
    int[] leftProduct = new int[n];
    int[] rightProduct = new int[n];

    leftProduct[0] = 1;
    for (int i = 1; i < n; i++) {
      leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
    }

    rightProduct[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) {
      rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
    }

    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      result[i] = leftProduct[i] * rightProduct[i];
    }

    return result;
  }

  // O(1) space
  public int[] productExceptSelf2(int[] nums) {

    if (nums.length == 0) {
      return new int[0];
    }

    int n = nums.length;
    int[] result = new int[n];

    result[0] = 1;
    for (int i = 1; i < n; i++) {
      result[i] = result[i - 1] * nums[i - 1];
    }

    int rightProduct = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      result[i] = result[i + 1] * rightProduct;
      rightProduct *= nums[i];
    }

    return result;
  }
}
