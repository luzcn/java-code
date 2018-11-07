package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// The gray code is a binary numeral system where two successive values differ in only one bit.
//
// Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
//
// A gray code sequence must begin with 0.
//
// Example 1:
//
// Input: 2
// Output: [0,1,3,2]
// Explanation:
// 00 - 0
// 01 - 1
// 11 - 3
// 10 - 2
//
// For a given n, a gray code sequence may not be uniquely defined.
// For example, [0,2,3,1] is also a valid gray code sequence.
//
// 00 - 0
// 10 - 2
// 11 - 3
// 01 - 1
// Example 2:
//
// Input: 0
// Output: [0]
// Explanation: We define the gray code sequence to begin with 0.
//              A gray code sequence of n has size = 2^n, which for n = 0 the size is 2^0 = 1.
//              Therefore, for n = 0 the gray code sequence is [0].
public class GrayCode_89 {

  // recursive + backtracking
  List<Integer> res = new ArrayList<>();
  HashSet<String> visited = new HashSet<>();

  public List<Integer> grayCode(int n) {

    StringBuilder num = new StringBuilder("0");
    for (int i = 1; i < n; i++) {
      num.append('0');
    }

    dfs(num.toString(), n, (int) Math.pow(2.0, n));
    return res;
  }

  private void dfs(String num, int n, int length) {
    if (res.size() == length) {
      return;
    }

    if (visited.contains(num)) {
      return;
    }

    res.add(convertToInt(num));
    visited.add(num);

    for (int i = 0; i < n; i++) {
      char c = num.charAt(i);

      if (c == '0') {
        dfs(num.substring(0, i) + '1' + num.substring(i + 1), n, length);
      } else {
        dfs(num.substring(0, i) + '0' + num.substring(i + 1), n, length);
      }
    }
  }

  private int convertToInt(String num) {
    return Integer.parseInt(num, 2);
  }


  // bit operation solution
  private List<Integer> grayCode_bit(int n) {
    int size = (int) Math.pow(2.0, n);

    for (int i = 0; i < size; i++) {
      res.add((i >> 1) ^ i);
    }

    return res;
  }
}
