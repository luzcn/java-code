package leetcode;

// Given a positive integer num, write a function which returns True if num is a perfect square else False.
//
// Note: Do not use any built-in library function such as sqrt.
//
// Example 1:
//
// Input: 16
// Returns: True
// Example 2:
//
// Input: 14
// Returns: False
public class ValidPerfectSquare {

  public boolean isPerfectSquare(int num) {

    // binary search

    if (num == 0 || num == 1) {
      return true;
    }

    int l = 0, r = num;

    while (l <= r) {
      int mid = l + (r - l) / 2;
      int value = num / mid;

      if (value == mid && num % mid == 0) {
        // exactly match
        return true;
      } else if (mid > value) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    return false;
  }
}
