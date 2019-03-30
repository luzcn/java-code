package leetcode;

import java.util.*;

// Given an array of n integers nums and a target,
// find the number of index triplets i, j, k with 0 <= i < j < k < n
// that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
// Example:
//
// Input: nums = [-2,0,1,3], and target = 2
// Output: 2
// Explanation: Because there are two triplets which sums are less than 2:
//              [-2,0,1]
//              [-2,0,3]
public class ThreeSumSmaller_259 {

  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);

    int n = nums.length;
    int count = 0;

    for (int i = 0; i < n - 2; i++) {
      int left = i + 1;
      int right = n - 1;
      // int a = nums[i];

      while (left < right) {
        if (nums[i] + nums[left] + nums[right] < target) {

          // all the numbers from [left, right] can be included in the result triples
          count += right - left;
          left++;
        } else {
          right--;
        }
      }
    }

    return count;
  }
}
