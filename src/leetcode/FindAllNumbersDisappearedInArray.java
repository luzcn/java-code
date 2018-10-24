package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//
// Find all the elements of [1, n] inclusive that do not appear in this array.
//
// Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
//
// Example:
//
// Input:
// [4,3,2,7,8,2,3,1]
//
// Output:
// [5,6]
public class FindAllNumbersDisappearedInArray {

  // counting sort idea, we know the range of each number is [1...n]
  // use the index as the "real" number
  // for each i, v=nums[i], we make flip to negative indicating this number is visited. e.g. nums[v-1] = -nums[v-1]
  // if we found an is already negative, the index+1 is the missing number
  public List<Integer> findDisappearedNumbers(int[] nums) {

    List<Integer> res = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      int index = Math.abs(nums[i]) - 1;

      if (nums[index] > 0) {
        // not visited yet, flip to negative
        nums[index] = -nums[index];
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        // the index is is not visited
        // so number i + 1 is missing
        res.add(i + 1);
      }
    }
    return res;
  }
}
