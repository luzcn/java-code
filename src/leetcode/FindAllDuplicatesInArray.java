package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//
// Find all the elements that appear twice in this array.
//
// Could you do it without extra space and in O(n) runtime?
public class FindAllDuplicatesInArray {

  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> res = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      int t = nums[i];

      while (nums[t - 1] != t) {
        int temp = nums[t - 1];
        nums[t - 1] = t;
        t = temp;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        res.add(nums[i]);
      }
    }
    return res;
  }
}
