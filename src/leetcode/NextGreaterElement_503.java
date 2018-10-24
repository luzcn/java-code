package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Given a circular array (the next element of the last element is the first element of the array),
// print the Next Greater Number for every element.
//
// The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
// which means you could search circularly to find its next greater number.
//
// If it doesn't exist, output -1 for this number.
//
// Example 1:
// Input: [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2;
// The number 2 can't find next greater number;
// The second 1's next greater number needs to search circularly, which is also 2.
// Note: The length of given array won't exceed 10000.
public class NextGreaterElement_503 {

  // similar to next greater 1
  // use stack, find and save the first greater element on it's right side,
  // - if cannot find save as null (do not use -1, since -1 can be a correct answer instead of not found)
  // - for each element, which is null in the saved array, search from [0...i - 1],
  //   if found larger elemenet, update the array

  public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;

    Integer[] right = new Integer[n];
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = n - 1; i >= 0; i--) {
      int num = nums[i];

      while (!stack.isEmpty() && stack.getLast() <= num) {
        stack.removeLast();
      }

      if (stack.isEmpty()) {
        right[i] = null;
      } else {
        right[i] = stack.getLast();
      }

      stack.addLast(num);
    }

    int[] res = new int[n];
    Arrays.fill(res, -1);
    for (int i = 0; i < n; i++) {
      if (right[i] == null) {
        for (int j = 0; j < i; j++) {
          if (nums[j] > nums[i]) {
            res[i] = nums[j];
            break;
          }
        }
      } else {
        res[i] = right[i];
      }
    }

    return res;
  }
}
