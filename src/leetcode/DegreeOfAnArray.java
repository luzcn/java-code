package leetcode;

import java.util.Collections;
import java.util.HashMap;

// Given a non-empty array of non-negative integers nums,
// the degree of this array is defined as the maximum frequency of any one of its elements.
//
// Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
//
// Example 1:
// Input: [1, 2, 2, 3, 1]
// Output: 2
// Explanation:
// The input array has a degree of 2 because both elements 1 and 2 appear twice.
// Of the subarrays that have the same degree:
// [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
// The shortest length is 2. So return 2.
//
// Example 2:
// Input: [1,2,2,3,1,4,2]
// Output: 6
// Note:
// - nums.length will be between 1 and 50,000.
// - nums[i] will be an integer between 0 and 49,999.
public class DegreeOfAnArray {

  // we can easily find the degree of entire array by counting frequency
  // if a number x is involved in the degree, we can save its left most appearance index and right most index
  // the subarray size is right[x] - left[x] + 1
  // so, use 3 hashmap save the information
  public int findShortestSubArray(int[] nums) {
    HashMap<Integer, Integer> count = new HashMap<>();
    HashMap<Integer, Integer> left = new HashMap<>();
    HashMap<Integer, Integer> right = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];

      // count the frequency
      count.put(num, count.getOrDefault(num, 0) + 1);

      // the left-most index of each number
      left.putIfAbsent(num, i);

      // the right most index of each number
      right.put(num, i);
    }

    int degree = Collections.max(count.values());

    int res = nums.length;
    for (int n : nums) {
      if (count.get(n) == degree) {
        res = Math.min(res, right.get(n) - left.get(n) + 1);
      }
    }

    return res;
  }
}
