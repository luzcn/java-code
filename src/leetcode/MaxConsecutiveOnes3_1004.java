package leetcode;

import java.util.*;

// Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
//
// Return the length of the longest (contiguous) subarray that contains only 1s.
//
//
//
// Example 1:
//
// Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
// Output: 6
// Explanation:
// [1,1,1,0,0,1,1,1,1,1,1]
// Bolded numbers were flipped from 0 to 1.
// The longest subarray is underlined.
public class MaxConsecutiveOnes3_1004 {

  public List<Integer> list = new ArrayList<>();

  public int longestOnes(int[] nums, int k) {
    int n = nums.length;
    int res = 0;
    int count = 0;
    // sliding window solution
    Deque<Integer> queue = new ArrayDeque<>();
    int sum = 0;

    for (int i = 0; i < n; i++) {
      if (nums[i] == 1) {
        queue.addLast(1);
        sum++;
      } else if (++count <= k) {
        queue.addLast(0);
        sum++;
      } else {
        while (!queue.isEmpty() && queue.getFirst() == 1) {
          queue.removeFirst();
          sum--;
        }
        queue.removeFirst();
        queue.addLast(0);
      }

      res = Math.max(res, sum);
    }
    return res;
  }

}
