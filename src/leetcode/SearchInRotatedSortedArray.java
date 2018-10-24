package leetcode;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return
 * -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0 Output: 4 Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3 Output: -1
 */
public class SearchInRotatedSortedArray {

  public int search(int[] nums, int target) {
    if (nums.length == 0) {
      return 0;
    }

    int l = 0, r = nums.length - 1;

    // binary search
    while (l <= r) {
      if (nums[l] == target) {
        return l;
      }

      if (nums[r] == target) {
        return r;
      }

      int m = l + (r - l) / 2;

      if (nums[m] == target) {
        return m;
      }

      if (nums[l] < nums[m]) {
        // on the first (not rotated) increasing part
        if (nums[m] < target) {
          l = m + 1;
        } else if (target < nums[l]) {
          l = m + 1;
        } else {
          r = m - 1;
        }
      } else {
        // on the second part

        if (nums[m] > target) {
          r = m - 1;
        } else if (target > nums[r]) {
          r = m - 1;
        } else {
          l = m + 1;
        }
      }
    }

    return -1;
  }
}
