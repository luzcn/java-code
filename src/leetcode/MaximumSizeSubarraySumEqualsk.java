package leetcode;

import java.util.HashMap;

// Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
// If there isn't one, return 0 instead.
//
// Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer
// range.
//
// Example 1: Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5, -2]
// sums to 3 and is the longest)
//
// Example 2: Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to 1
// and is the longest)
//
public class MaximumSizeSubarraySumEqualsk {

  // For i in [0...n-1]
  // - compute sum += sum[i], we can get a sequence of summary values, save them in a hashmap.
  // - if we find "sum==k", then i + 1 is the max length candidate
  // - if sum !=k, but "sum-k" is already in previous sum sequence, the i - map[sum-k] is also candidate
  public int maxSubArrayLen(int[] nums, int k) {

    int sum = 0;
    int maxLength = 0;
    HashMap<Integer, Integer> mapSumIndex = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      if (sum == k) {
        maxLength = i + 1;
      } else {
        if (mapSumIndex.containsKey(sum - k)) {
          maxLength = Math.max(maxLength, i - mapSumIndex.get(sum - k));
        }
      }

      // if the current sum is already in the hash map
      // no need to update the index, because we need the max length
      if (!mapSumIndex.containsKey(sum)) {
        mapSumIndex.put(sum, i);
      }

      // mapSumIndex.putIfAbsent(sum, i);
    }

    return maxLength;
  }
}
