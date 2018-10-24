package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// Given a positive integer n, find the least number of perfect square numbers
// (for example, 1, 4, 9, 16, ...) which sum to n.
//
// Example 1:
// Input: n = 12
// Output: 3
// Explanation: 12 = 4 + 4 + 4.
//
// Example 2:
// Input: n = 13
// Output: 2
// Explanation: 13 = 4 + 9.
public class PerfectSquare {

  private boolean isPerfect(int n) {
    if (n < 0) {
      return false;
    }

    int sqrtValue = (int) Math.sqrt(n);
    return sqrtValue * sqrtValue == n;
  }

  // bfs solution,
  // for each n, we can consider as 1 + (n-1), 4 + (n-4) ....
  // find the shortest path
  public int numSquares(int n) {

    if (n <= 0) {
      return 0;
    }

    if (isPerfect(n)) {
      return 1;
    }

    Queue<Integer> candidates = new LinkedList<>();
    Queue<Integer> temp = new LinkedList<>();
    int level = 1;
    candidates.add(n);

    while (!candidates.isEmpty()) {
      int current = candidates.poll();

      for (int first = 1; first <= current / 2; first++) {

        int second = current - first;
        if (isPerfect(first)) {
          if (isPerfect(second)) {
            return level + 1;
          } else {
            temp.add(second);
          }
        }
      }

      if (candidates.isEmpty()) {
        level++;
        candidates = temp;
        temp = new LinkedList<>();
      }
    }

    return level;
  }
}
