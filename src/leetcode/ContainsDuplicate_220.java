package leetcode;

import java.util.HashMap;
import java.util.TreeSet;

// Given an array of integers, find out whether there are two distinct indices i and j in the array such that
// - the absolute difference between nums[i] and nums[j] is at most t
// - and the absolute difference between i and j is at most k.
//
// Example 1:
//
// Input: nums = [1,2,3,1], k = 3, t = 0
// Output: true
// Example 2:
//
// Input: nums = [1,0,1,1], k = 1, t = 2
// Output: true
// Example 3:
//
// Input: nums = [1,5,9,1,5,9], k = 2, t = 3
// Output: false
public class ContainsDuplicate_220 {

  // use TreeSet to save the window of k elements
  // for each n, find the first number that are greater than n,  set.ceiling(n)
  // and the first number that is less than n set.floor(n)
  // O(n * log(k))
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

    if (nums.length == 0 || k < 0) {
      return false;
    }

    TreeSet<Integer> set = new TreeSet<>();

    for (int i = 0; i < nums.length; i++) {

      Integer s = set.ceiling(nums[i]);

      // Find the first number that are greater than current element
      if (s != null && nums[i] >= s - t) {
        return true;
      }

      // Find the first number that are less than current element
      Integer g = set.floor(nums[i]);
      if (g != null && nums[i] <= g + t) {
        return true;
      }

      set.add(nums[i]);

      if (set.size() > k) {
        set.remove(nums[i - k]);
      }
    }

    return false;
  }


  // use bucket
  public boolean containsNearbyAlmostDuplicateBucket(int[] nums, int k, int t) {
    if (k < 1 || t < 0) {
      return false;
    }

    HashMap<Long, Long> map = new HashMap<>();

    // choose t+1 as the bucket size.
    for (int i = 0; i < nums.length; i++) {
      long remappedNum = (long) nums[i] - Integer.MIN_VALUE;

      long bucket = remappedNum / ((long) t + 1);

      if (map.containsKey(bucket) || (map.containsKey(bucket - 1)
          && remappedNum - map.get(bucket - 1) <= t) || (
          map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)) {
        return true;
      }

      map.put(bucket, remappedNum);

      if (map.size() > k) {
        long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);

        map.remove(lastBucket);
      }
    }

    return false;
  }
}
