package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// Given a positive integer n and you can do operations as follow:
//
// - If n is even, replace n with n/2.
// - If n is odd, you can replace n with either n + 1 or n - 1.
//
// What is the minimum number of replacements needed for n to become 1?
//
// Example 1:
// Input:
// 8
//
// Output:
// 3
// Explanation:
// 8 -> 4 -> 2 -> 1
//
// Example 2:
// Input:
// 7
// Output:
// 4
// Explanation:
// 7 -> 8 -> 4 -> 2 -> 1
// or
// 7 -> 6 -> 3 -> 2 -> 1
public class integerReplacement {

  public int integerReplacementBFS(int n) {

    if (n == Integer.MAX_VALUE) {
      return 32;
    }

    // bfs solution
    Queue<Integer> queue = new LinkedList<>();
    Queue<Integer> temp = new LinkedList<>();
    queue.offer(n);

    int level = 0;

    while (!queue.isEmpty()) {
      int current = queue.poll();

      if (current == 1) {
        return level;
      }

      if (current % 2 == 0) {
        temp.offer(current / 2);
      } else {
        temp.offer(current - 1);
        temp.offer(current + 1);
      }

      if (queue.isEmpty()) {
        level++;
        queue = temp;
        temp = new LinkedList<>();
      }

    }

    return level;
  }

  // use bit manipulation
  public int integerReplacement(int n) {

    // if n is even the steps are fixed
    // if n is odd, n = 2k + 1, then n + 1 = 2k+2, if (n+1) % 4== 0, then (k + 1) is also even, so we can divide twice

    if (n == Integer.MAX_VALUE) {
      return 32;
    }

    int count = 0;
    while (n > 1) {
      if (n % 2 == 0) {
        n /= 2;
      } else {

        if ((n + 1) % 4 == 0 && n != 3) {
          n++;
        } else {
          n--;
        }
      }
    }

    return count;
  }
}
