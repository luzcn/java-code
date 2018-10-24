package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous
 * subarrays whose sum equals to k.
 *
 * Example 1: Input:nums = [1,1,1], k = 2 Output: 2 Note: The length of the array is in range [1,
 * 20,000]. The range of numbers in the array is [-1000, 1000] and the range of the integer k is
 * [-1e7, 1e7].
 */
public class SubarraySumEqualsK {

  // dp idea, use a hash map to save all the accumulate sum of each i from [0...n),
  // the value is the total number of subarrays which have this sum
  //
  // if we find sum == k, then it's a valid continuous subarray [0..i]
  // otherwise, if we find sum - k exists in the hash map, the subarray [map.get(sum-k) + 1..i] is a valid result

  public int subarraySum(int[] nums, int k) {
    if (nums.length == 0) {
      return 0;
    }

    int sum = 0;
    int result = 0;
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      if (sum == k || map.containsKey(sum - k)) {
        result++;
      }

      map.put(sum, i);

    }

    return result;
  }
}
