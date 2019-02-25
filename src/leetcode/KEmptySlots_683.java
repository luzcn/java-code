package leetcode;

import java.util.*;

// There is a garden with N slots. In each slot, there is a flower.
//
// The N flowers will bloom one by one in N days.
// In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
//
// Given an array flowers consists of number from 1 to N.
// Each number in the array represents the place where the flower will open in that day.
//
// For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x,
// where i and x will be in the range from 1 to N.
//
// Also given an integer k, you need to output in which day there exists two flowers in the status of blooming,
// and also the number of flowers between them is k and these flowers are not blooming.
//
// If there isn't such day, output -1.
//
// Example 1:
// Input:
// flowers: [1,3,2]
// k: 1
// Output: 2
// Explanation: In the second day, the first and the third flower have become blooming.
// Example 2:
// Input:
// flowers: [1,2,3]
// k: 1
// Output: -1
public class KEmptySlots_683 {

  public int kEmptySlots(int[] flowers, int k) {
    return 0;
  }


  // sliding window idea:
  // - construct an array days[] to indicate at ith position, which day bloomed.
  // - we can observe that a valid k-slots in array days[l, r], days[l] and days[r] should be minimum of these sub-array
  // and the length is k+1;
  // - if we find some i, such that days[i] < days[l] or days[i] < days[r] (it means this sub-arrays is not a valid k-slot),
  // all k-length sub-array starting from j, where j < i, is not valid either. we should directly moved to ith position
  public int kEmptySlotsSlidingWindow(int[] flowers, int k) {

    int n = flowers.length;
    int[] days = new int[n];

    for (int i = 0; i < n; i++) {
      int x = flowers[i];

      days[x - 1] = i + 1;
    }

    int left = 0;
    int right = k + 1;

    int res = Integer.MAX_VALUE;

    while (right < n) {

      boolean isValid = true;
      for (int i = left + 1; i <= right - 1; i++) {
        if (days[i] < days[left] || days[i] < days[right]) {
          left = i;
          right = left + k + 1;
          isValid = false;
          break;
        }
      }

      if (isValid) {

        // find a valid sub-array, record the bloom day
        res = Math.min(res, Math.max(days[left], days[right]));

        // all days[left+1, right-1] should > days[right], so directly move to right index
        left = right;
        right = left + k + 1;
      }
    }

    return res == Integer.MAX_VALUE ? -1 : res;
  }

  // O(n^2) average time complexity
  public int kEmptySlotsBruteForce(int[] flowers, int k) {
    int n = flowers.length;

    boolean[] bloomed = new boolean[n + 1];

    for (int i = 0; i < n; i++) {
      int x = flowers[i];

      int right = x + k + 1;
      int left = x - k - 1;

      if (left >= 0 && bloomed[left] && isValid(bloomed, left + 1, x - 1)) {
        return i + 1;
      }

      if (right <= n && bloomed[right] && isValid(bloomed, x + 1, right - 1)) {
        return i + 1;
      }

      bloomed[x] = true;
    }

    return -1;
  }

  private boolean isValid(boolean[] bloomed, int i, int j) {

    for (int x = i; x <= j; x++) {
      if (bloomed[x]) {
        return false;
      }
    }

    return true;
  }
}
