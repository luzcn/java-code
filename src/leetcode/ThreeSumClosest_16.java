package leetcode;

import java.util.*;

// Given an array S of n integers, find three integers in S such that the sum is closest to a
// given number, target. Return the sum of the three integers.
//
// You may assume that each input would have exactly one solution.
//
// For example, given array S = {-1 2 1 -4}, and target = 1.
//
// The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
///

public class ThreeSumClosest_16 {

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    int l, r, sum;
    int closest = Integer.MAX_VALUE;
    int result = 0;

    for (int i = 0; i < nums.length - 2; i++) {
      l = i + 1;
      r = nums.length - 1;

      while (l < r) {
        sum = nums[i] + nums[l] + nums[r];

        if (sum == target) {
          return sum;
        } else if (sum > target) {
          r--;
        } else {
          l++;
        }

        // compute the distance
        int distance = Math.abs(target - sum);
        if (distance < closest) {
          // save the sum to result
          // and update the closet
          result = sum;
          closest = distance;
        }
      }
    }
    return result;
  }

}
