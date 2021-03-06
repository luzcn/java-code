package leetcode;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is
 * missing from the array.
 *
 * Example 1
 *
 * Input: [3,0,1] Output: 2 Example 2
 *
 * Input: [9,6,4,2,3,5,7,0,1] Output: 8
 *
 * Thought: similar to counting sort make the array to A[0] = 0; A[1] = 1...A[i] = i
 */
public class MissingNumber {

  public int findMissingNuber(int[] nums) {
    int n = nums.length;

    for (int i = 0; i < nums.length; i++) {
      int value = nums[i];

      while (value > 0 && value <= n && nums[value - 1] != value) {
        // if the value is in the range [0...n-1], swap the value and nums[value]
        // otherwise don't move it.
        int temp = nums[value - 1];
        nums[value - 1] = value;
        value = temp;
      }
    }

    int i = 0;
    for (; i < n; i++) {
      if (nums[i] != i + 1) {
        break;
      }
    }

    return i + 1;
  }
}
