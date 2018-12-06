package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
//
// Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
//
// You may return any answer array that satisfies this condition.
//
//
//
// Example 1:
//
// Input: [4,2,5,7]
// Output: [4,5,2,7]
// Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
//
//
// Note:
//
// 2 <= A.length <= 20000
// A.length % 2 == 0
// 0 <= A[i] <= 1000
public class SortArrayByParity2_922 {

  // O(n) space
  public int[] sortArrayByParity2(int[] A) {
    int m = A.length;

    Deque<Integer> odds = new ArrayDeque<>();
    Deque<Integer> evens = new ArrayDeque<>();

    for (int num : A) {
      if (num % 2 == 0) {
        evens.add(num);
      } else {
        odds.add(num);
      }
    }

    for (int i = 0; i < m; i++) {
      if (i % 2 == 0) {
        A[i] = evens.getFirst();
        evens.removeFirst();
      } else {
        A[i] = odds.getFirst();
        odds.removeFirst();
      }
    }
    return A;
  }
}
