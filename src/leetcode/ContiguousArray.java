package leetcode;

import java.util.HashMap;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and
 * 1.
 *
 * Example 1: Input: [0,1] Output: 2 Explanation: [0, 1] is the longest contiguous subarray with
 * equal number of 0 and 1. Example 2: Input: [0,1,0] Output: 2 Explanation: [0, 1] (or [1, 0]) is a
 * longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {

  public int findBruteForce(int[] nums) {
    int maxLength = 0;

    for (int i = 0; i < nums.length; i++) {
      int zeros = 0;
      int ones = 0;
      for (int j = i; j < nums.length; j++) {
        if (nums[j] == 0) {
          zeros++;
        } else {
          ones++;
        }

        if (zeros == ones) {
          maxLength = Math.max(maxLength, j - i + 1);
        }
      }
    }

    return maxLength;
  }

  // use and auxiliary variable to count 1 and 0, when find 1, increase by 1, found 0, decrease by 1
  // when we see count == 0, which means we found subarray [0...i] has equal numbers of 1 and 0
  // and if we see the count twice, it means subarray [j...i] also has equal numbers of 1 and 0
  // we can use a hashmap to save the count and index
  public int findMaxLength(int[] nums) {
    if (nums.length < 2) {
      return 0;
    }

    int n = nums.length;
    int maxLength = 0;
    int count = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      if (nums[i] == 0) {
        count--;
      } else {
        count++;
      }

      if (count == 0) {
        maxLength = Math.max(maxLength, i + 1);
      } else if (map.containsKey(count)) {
        maxLength = Math.max(maxLength, i - map.get(count));
      }

      // need to find the max length,
      // so if the count already in the map, no need to update.
      if (!map.containsKey(count)) {
        map.put(count, i);
      }
    }
    return maxLength;
  }
}
