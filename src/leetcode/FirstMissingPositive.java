package leetcode;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 *
 * Your algorithm should run in O(n) time and uses constant space.
 *
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * Thoughts: similar to Missing number problem use counting sort idea
 *
 * note: 0 is not considered as positive integer. so we need to make A[i-1] = i
 */
public class FirstMissingPositive {

  public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      int value = nums[i];

      while (value > 0 && value <= n && nums[value - 1] != value) {
        // swap value and nums[value]
        int temp = nums[value - 1];
        nums[value - 1] = value;
        value = temp;
      }
    }

    int result = -1;
    for (int i = 0; i < n; i++) {
      if (nums[i] != i + 1) {
        result = i + 1;
        break;
      }
    }

    return result == -1 ? n + 1 : result;
  }
}
