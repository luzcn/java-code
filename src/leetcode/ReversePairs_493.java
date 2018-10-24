package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
//
// You need to return the number of important reverse pairs in the given array.
//
// Example1:
//
// Input: [1,3,2,3,1]
// Output: 2
// Example2:
//
// Input: [2,4,3,5,1]
// Output: 3
public class ReversePairs_493 {

  public int reversePairs(int[] nums) {

    return mergeSort(nums, 0, nums.length - 1);
  }


  private int mergeSort(int[] nums, int l, int r) {

    if (l >= r) {
      return 0;
    }

    int mid = l + (r - l) / 2;

    int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);

    int j = mid + 1;
    for (int i = l; i <= mid; i++) {
      while (j <= r && (long) nums[i] > (long) 2 * nums[j]) {
        j++;
      }

      count += j - (mid + 1);
    }

    merge(nums, l, mid, r);

    return count;
  }

  private void merge(int[] nums, int l, int mid, int r) {
    List<Integer> temp = new ArrayList<>();

    int i = l;
    int j = mid + 1;
    while (i <= mid && j <= r) {
      if (nums[i] < nums[j]) {
        temp.add(nums[i]);
        i++;
      } else {
        temp.add(nums[j]);
        j++;
      }
    }

    while (i <= mid) {
      temp.add(nums[i]);
      i++;
    }

    while (j <= r) {
      temp.add(nums[j]);
      j++;
    }

    for (i = 0; i < temp.size(); i++) {
      nums[l + i] = temp.get(i);
    }
  }

  public int bruteForce(int[] nums) {
    int count = 0;
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {

        if (nums[i] > 2 * nums[j]) {
          count++;
        }
      }
    }

    return count;
  }
}
