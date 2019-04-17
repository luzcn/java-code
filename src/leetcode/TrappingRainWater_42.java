package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// Given n non-negative integers representing an elevation map where the width of each bar is 1,
// compute how much water it is able to trap after raining.
//
//
// The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
// In this case, 6 units of rain water (blue section) are being trapped.
//
// Example:
//
// Input: [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
public class TrappingRainWater_42 {

  // the general idea is for each position i, we need to know the max height on it's left side [0..i-1]
  // and the max height on it's right side [i+1...n-1].
  // the brute force solution takes O(n^2)
  //
  // use dp idea, scan twice from left to right and right to left
  // save the highest bar found so far
  // then we reduce to O(n) time and O(n) space
  public int trap(int[] height) {
    if (height.length == 0) {
      return 0;
    }

    int n = height.length;

    int[] left = new int[n];
    left[0] = height[0];
    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], height[i]);
    }

    int[] right = new int[n];
    right[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], height[i]);
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += Math.min(left[i], right[i]) - height[i];
    }

    return sum;
  }


  // using stack
  private int trapStack(int[] height) {
    int index = 0;
    int sum = 0;
    int n = height.length;

    Deque<Integer> stack = new ArrayDeque<>();

    while (index < n) {

      while (!stack.isEmpty() && height[stack.getLast()] < height[index]) {

        int current = stack.getLast();
        stack.removeLast();

        if (stack.isEmpty()) {
          break;
        }

        int left = stack.getLast();

        int dis = index - left - 1;
        sum += dis * (Math.min(height[left], height[index]) - height[current]);
      }

      stack.addLast(index++);
    }

    return sum;
  }

  // O(1) space
  private int trap2(int[] height) {

    int sum = 0;
    int bound = 0;
    int left = 0;
    int right = height.length - 1;

    while (left < right) {
      if (height[left] < height[right]) {
        bound = Math.max(bound, height[left]);

        sum += bound - height[left];
        left++;
      } else {
        bound = Math.max(bound, height[right]);

        sum += bound - height[right];

        right--;
      }
    }

    return sum;
  }
}
