package leetcode;

import java.util.Stack;

// Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
// find the area of largest rectangle in the histogram.
///
public class LargestRectangleInHistogram_84 {

  // DP idea
  // - For each bar, extends in left and right two direction
  // - increase the count if found larger number, else stops.
  // - left[i] = the longest distance can extend in left direction
  // - right[i] = the longest distance thant can extend in right direction
  //
  // so, the rectangle area of each bar is height[i] * (left[i] + right[i] + 1)
  // Now, the key problem is how to build the left and right array
  // - if use brute force, it takes O(n^2)
  // - we can use a stack to save the left bound of each height
  // - if stack is empty, push the current index
  // - if stack top is larger than the current height, pop the stack
  // - repeat these steps until eight the stack is empty (left[i] is i) or the stack top is smaller (left[i] is i - top)
  public int largestRectangleArea(int[] heights) {
    if (heights.length == 0) {
      return 0;
    }

    int n = heights.length;
    int[] left = new int[n];
    int[] right = new int[n];
    int maxArea = 0;

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {

      while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        left[i] = i;
      } else {
        // stack top element is not included in the left extends distance
        // so need to -1
        left[i] = i - stack.peek() - 1;
      }
      stack.push(i);
    }

    stack.clear();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        right[i] = (n - 1) - i;
      } else {
        right[i] = stack.peek() - i - 1;
      }

      stack.push(i);
    }

    for (int i = 0; i < n; i++) {
      maxArea = Math.max(maxArea, heights[i] * (left[i] + right[i] + 1));
    }

    return maxArea;

  }

}
