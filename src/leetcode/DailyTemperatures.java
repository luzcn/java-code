package leetcode;

import java.util.Stack;


// Given a list of daily temperatures, produce a list that, for each day in the input,
// tells you how many days you would have to wait until a warmer temperature.
//
// If there is no future day for which this is possible, put 0 instead.
//
// For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
//
// Note: The length of temperatures will be in the range [1, 30000].
// Each temperature will be an integer in the range [30, 100].
public class DailyTemperatures {

  public int[] dailyTemperatures(int[] temperatures) {

    int n = temperatures.length;
    int[] res = new int[n];

    // use stack
    // O(N) time, O(W) space, w is the length of contiguous increasing subarray
    Stack<Integer> stack = new Stack<>();

    // iterate from the last element
    for (int i = n - 1; i >= 0; i--) {
      // if current temperature is higher than stack peek, keep popping stack, until empty or peek is higher than current
      while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
        stack.pop();
      }

      res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
      stack.push(i);
    }

    return res;
  }


  // brute force solution
  private int[] dailyTemperaturesBF(int[] temperatures) {

    int n = temperatures.length;

    if (temperatures.length == 0) {
      return temperatures;
    }
    int[] res = new int[n];

    for (int i = 0; i < n; i++) {
      int j = i + 1;
      while (j < n && temperatures[j] <= temperatures[i]) {
        j++;
      }

      if (j == n) {
        res[i] = 0;
      } else {
        res[i] = j - i;
      }
    }

    return res;
  }
}
