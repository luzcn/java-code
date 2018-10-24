package leetcode;

import java.util.Arrays;

// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
// The replacement must be in-place and use only constant extra memory.
//
// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//
// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1
public class NextPermutation {

  // From observation, we can find, if the array is in descending order, no next larger permutation available
  // so the idea is:
  // 1. from right to left, find the first element that satisfy a[i] > a[i-1],
  // now all elements on right side of i-1 cannot re-arrange to get a larger permutation, because they are in descending order
  // 2. now, on the right side of i-1, find the smallest number a[j], which is also larger than a[i-1]
  // 3. swap a[j] and a[i-1]
  // 4. we find a next permutation, but we need to get the next smallest permutation, so need to sort the a[i] to a[n-1]

  public void nextPermutation(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }

    int n = nums.length;

    if (nums[n - 2] < nums[n - 1]) {
      swap(nums, n - 2, n - 1);
      return;
    }

    int i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }

    // now i is the first decreasing number from right to left of the array
    // find the smallest number which is also larger than this nums[i]
    if (i >= 0) {
      int index = n - 1;
      while (index >= 0 && nums[index] <= nums[i]) {
        index--;
      }

      swap(nums, i, index);
    }

    // sort [i+1...n-1]
    Arrays.sort(nums, i + 1, n);
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }

    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

}
