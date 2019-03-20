package leetcode;

import java.util.Arrays;

// Given an array of integers nums and a positive integer k,
// find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
//
//
//
// Example 1:
//
// Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
// Output: True
// Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
public class PartitionToKEqualSumSubsets_698 {

  public boolean canPartitionKSubsets(int[] nums, int k) {

    int n = nums.length;
    int sum = Arrays.stream(nums).sum();

    // the total sum must be even number
    if (sum % k != 0) {
      return false;
    }

    int target = sum / k;

    Arrays.sort(nums);
    int index = n - 1;

    if (nums[index] > target) {
      return false;
    }

    while (index >= 0 && nums[index] == target) {
      index--;
      k--;
    }

    return dfs(nums, target, index, new int[k]);


  }

  // O(k^N) time
  private boolean dfs(int[] nums, int target, int index, int[] groups) {

    if (index < 0) {
      return true;
    }

    int value = nums[index];

    for (int i = 0; i < groups.length; i++) {
      if (groups[i] + value <= target) {
        groups[i] += value;

        if (dfs(nums, target, index - 1, groups)) {
          return true;
        }

        groups[i] -= value;
      }
    }

    return false;
  }
}
