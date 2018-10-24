package leetcode;

import java.util.LinkedList;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that
 * the new number is the smallest possible.
 *
 * Note: The length of num is less than 10002 and will be ≥ k. The given num does not contain any
 * leading zero. Example 1:
 *
 * Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the three digits 4, 3, and 2 to
 * form the new number 1219 which is the smallest. Example 2:
 *
 * Input: num = "10200", k = 1 Output: "200" Explanation: Remove the leading 1 and the number is
 * 200. Note that the output must not contain leading zeroes. Example 3:
 *
 * Input: num = "10", k = 2 Output: "0" Explanation: Remove all the digits from the number and it is
 * left with nothing which is 0.
 */
public class RemoveKDigits {

  StringBuilder result;

  private void dfs(String num, int k, int index) {

    if (index >= num.length()) {
      return;
    }

    if (k == 0) {
      if (num.compareTo(result.toString()) < 0) {
        result = new StringBuilder(num);
      }
    }

    for (int i = index; i < num.length(); i++) {
      String newNumber = num.substring(0, i) + num.substring(i + 1);

      dfs(newNumber, k - 1, i);
    }
  }


  // similar to create max number from 1 array
  // here need to create the smallest number with nums.length - k digits
  public String removeKdigits(String num, int k) {
    if (k >= num.length()) {
      return "0";
    }

    LinkedList<Character> digits = new LinkedList<>();
    int len = num.length() - k;

    for (int i = 0; i < num.length(); i++) {
      char c = num.charAt(i);

      while (!digits.isEmpty() && digits.peekLast() > c
          && digits.size() - 1 + num.length() - i >= len) {
        digits.removeLast();
      }

      if (digits.size() < len) {
        digits.addLast(c);
      }
    }

    StringBuilder res = new StringBuilder();
    for (char c : digits) {
      res.append(c);
    }

    while (res.length() > 1 && res.charAt(0) == '0') {
      res.deleteCharAt(0);
    }

    return res.toString();
  }
}
