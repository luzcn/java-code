package datastructure.Array;

public class BinarySearch {

  // Given a sorted array, find the index of a given target value, if not find, return the proper insert position
  // You may assume no duplicates in the array.
  //
  // Example 1:
  //
  // Input: [1,3,5,6], 5
  // Output: 2
  // Example 2:
  //
  // Input: [1,3,5,6], 2
  // Output: 1
  //
  // Example 3:
  //
  // Input: [1,3,5,6], 7
  // Output: 4
  // Example 4:
  //
  // Input: [1,3,5,6], 0
  // Output: 0
  int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left;
  }
}
