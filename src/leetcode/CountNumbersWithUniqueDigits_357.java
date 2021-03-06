package leetcode;

// Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
//
// Example:
// Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100,
// excluding [11,22,33,44,55,66,77,88,99])
public class CountNumbersWithUniqueDigits_357 {

  // https://leetcode.com/problems/count-numbers-with-unique-digits/discuss/83061/Java-O(1)-with-explanation
  // This is a digit combination problem. Can be solved in at most 10 loops.
  //
  // When n == 0, return 1. I got this answer from the test case.
  //
  // When n == 1, _ can put 10 digit in the only position. [0, ... , 10]. Answer is 10.
  //
  // When n == 2, _ _ first digit has 9 choices [1, ..., 9], second one has 9 choices excluding the already chosen one.
  // So totally 9 * 9 = 81. answer should be 10 + 81 = 91
  //
  // When n == 3, _ _ _ total choice is 9 * 9 * 8 = 684. answer is 10 + 81 + 648 = 739
  //
  // When n == 4, _ _ _ _ total choice is 9 * 9 * 8 * 7.
  //
  // ...
  //
  // When n == 10, _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1
  //
  // When n == 11, _ _ _ _ _ _ _ _ _ _ _ total choice is 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 * 0 = 0
  public int countNumbersWithUniqueDigits(int n) {

    if (n == 0) {
      return 1;
    }

    int res = 0;

    for (int i = 1; i <= n; i++) {
      res += count(i);
    }

    return res;
  }

  // f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
  private int count(int k) {
    if (k < 1) {
      return 0;
    }

    if (k == 1) {
      return 10;
    }

    int res = 1;

    for (int i = 9; i >= (9 - k + 2); i--) {
      res *= i;
    }

    return res;
  }
}
