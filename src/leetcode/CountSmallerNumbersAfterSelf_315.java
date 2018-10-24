package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// you are given an integer array nums and you have to return a new counts array.
// The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
//
// Example:
//
// Input: [5,2,6,1]
// Output: [2,1,1,0]
// Explanation:
// To the right of 5 there are 2 smaller elements (2 and 1).
// To the right of 2 there is only 1 smaller element (1).
// To the right of 6 there is 1 smaller element (1).
// To the right of 1 there is 0 smaller element.
public class CountSmallerNumbersAfterSelf_315 {

  // insertion sort + binary search
  // the index of ith number in the sorted array is the number of smaller elements
  public List<Integer> countSmaller(int[] nums) {
    int n = nums.length;

    List<Integer> res = new ArrayList<>(Collections.nCopies(n, 0));
    List<Integer> sorted = new ArrayList<>();

    for (int i = n - 1; i >= 0; i--) {
      int low = 0;
      int high = sorted.size();

      while (low < high) {
        int mid = low + (high - low) / 2;

        if (sorted.get(mid) >= nums[i]) {
          high = mid;
        } else {
          low = mid + 1;
        }
      }

      res.set(i, high);
      sorted.add(high, nums[i]);
    }

    return res;
  }
}
