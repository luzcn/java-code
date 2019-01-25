package leetcode;

import java.util.Arrays;
import java.util.HashMap;

// Given an array of integers and an integer k,
// you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j),
// where i and j are both numbers in the array and their absolute difference is k.
//
// Example 1:
// Input: [3, 1, 4, 1, 5], k = 2
// Output: 2
// Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
// Although we have two 1s in the input, we should only return the number of unique pairs.
// Example 2:
// Input:[1, 2, 3, 4, 5], k = 1
// Output: 4
// Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
// Example 3:
// Input: [1, 3, 1, 5, 4], k = 0
// Output: 1
// Explanation: There is one 0-diff pair in the array, (1, 1).
public class KDiffPairsInArray_532 {

  // The problem needs to find the unique pair
  // and i, j not the same number.
  // but unique and absolute value has some overlap
  // i.e. |i=1,j=2| is 1, and |i=2,j=1| is also 1, and they are not unique pair
  // so we don't need to check the absolute value, we only need to see if nums[i] - k in the hashmap


  // construct-and-check pattern is interesting,
  // also we should use HashSet
  public int findPairs(int[] nums, int k) {
    int n = nums.length;
    int res = 0;

    HashMap<Integer, Integer> map = new HashMap<>();
    Arrays.sort(nums);

    int i = 0;

    // using construct-and-check pattern can avoid checking if i,j are the same indexed value
    while (i < n) {

      if (map.get(nums[i] - k) != null) {

        map.putIfAbsent(nums[i], i);
        res++;
        i++;

        // skip the duplicates
        while (i < n && nums[i - 1] == nums[i]) {
          i++;
        }
      } else {
        map.putIfAbsent(nums[i], i);
        i++;
      }
    }

    return res;
  }
}
