package leetcode;

/**
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where num[i] ≠ num[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is
 * fine.
 *
 * You may imagine that num[-1] = num[n] = -∞.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1] Output: 2 Explanation: 3 is a peak element and your function should
 * return the index number 2. Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4] Output: 1 or 5 Explanation: Your function can return either index
 * number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 * Note: Your solution should be in logarithmic complexity.
 */
public class FindPeakElement {


  public int findPeakElement(int[] nums) {
    int n = nums.length;
    if (n == 0 || n == 1) {
      return 0;
    }

    // checking the first and last element first
    if (nums[0] > nums[1]) {
      return 0;
    }

    if (nums[n - 1] > nums[n - 2]) {
      return n - 1;
    }

    // we can return any of the peak position,
    // so use binary search
    // the left and right bound should be [1...n-2]
    int l = 1;
    int r = n - 2;

    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
        return mid;
      } else if (nums[mid] < nums[mid + 1]) {
        // nums[mid + 1] is a peak candidate
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return l;
  }

  // the linear brute force solution
  public int findPeakElementLinear(int[] nums) {
    if (nums.length == 0 || nums.length == 1) {
      return 0;
    }

    if (nums[0] > nums[1]) {
      return 0;
    }

    for (int i = 1; i < nums.length - 1; i++) {
      if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
        return i;
      }
    }

    return nums.length - 1;

  }
}
