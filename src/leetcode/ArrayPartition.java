package leetcode;

import java.util.Arrays;

// Given an array of 2n integers, your task is to group these integers into n pairs of integer,
// say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
//
// Example 1:
// Input: [1,4,3,2]
// Output: 4
// Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
//
// Note:
//  - n is a positive integer, which is in the range of [1, 10000].
//  - All the integers in the array will be in the range of [-10000, 10000].
public class ArrayPartition {

  // we can observe, if we group [min, max] a pair, the max is completely wasted here.
  // so we should group the min value with any number as smaller as possible, which obviously is the second-smallest
  // 1. sort the array
  // 2. group the consecutive numbers in a pair, (A[i], A[i+1]), since need to take min, also choose A[i]
  public int arrayPairSum(int[] nums) {

    if (nums == null || nums.length == 0) {
      return 0;
    }

    int totalSum = 0;
    // sort in ascending order
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i = i + 2) {
      totalSum += nums[i];
    }

    return totalSum;
  }
}
