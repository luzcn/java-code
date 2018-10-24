package leetcode;

// Given an array of integers nums sorted in ascending order,
// find the starting and ending position of a given target value.
//
// Your algorithm's runtime complexity must be in the order of O(log n).
//
// If the target is not found in the array, return [-1, -1].
//
// Example 1:
//
// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]
// Example 2:
//
// Input: nums = [5,7,7,8,8,10], target = 6
// Output: [-1,-1]
public class SearchForRange_34 {

  public int[] searchRange(int[] nums, int target) {

    int l = 0;
    int r = nums.length - 1;

    int pos = -1;

    while (l <= r) {
      int mid = l + (r - l) / 2;

      if (target == nums[mid]) {
        pos = mid;
        break;
      } else if (target < nums[mid]) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    if (pos == -1) {
      return new int[]{-1, -1};
    }

    int first = pos;
    int second = pos;
    int[] res = new int[2];
    while (first >= 0 && nums[first] == target) {
      res[0] = first;
      first--;
    }

    while (second < nums.length && nums[second] == target) {
      res[1] = second;
      second++;
    }

    return res;
  }
}
