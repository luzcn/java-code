package leetcode;

import java.util.*;

// Given a string containing just the characters '(' and ')',
// find the length of the longest valid (well-formed) parentheses substring.
//
// Example 1:
//
// Input: "(()"
// Output: 2
// Explanation: The longest valid parentheses substring is "()"
// Example 2:
//
// Input: ")()())"
// Output: 4
// Explanation: The longest valid parentheses substring is "()()"
public class LongestValidParentheses_32 {

  public int longestValidParentheses(String s) {

    // dp idea
    // counting the '(' and ')', if we see '(', increase, otherwise decrease
    // - if we see count == 0, we find a valid substring, record the current length
    // - if we have count < 0, the we reset the count to 0, and also the current length to 0
    // - scan both from left->right and right->left

    int count = 0;
    int currentLength = 0;
    int res = 0;

    // scan from left to right
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(') {
        count++;
      } else if (c == ')') {
        count--;
      }

      currentLength++;
      if (count == 0) {
        // find a valid substring
        res = Math.max(res, currentLength);
      }

      if (count < 0) {
        count = 0;
        currentLength = 0;
      }
    }

    count = 0;
    currentLength = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);

      if (c == ')') {
        count++;
      } else if (c == '(') {
        count--;
      }

      currentLength++;
      if (count == 0) {
        // find a valid substring
        res = Math.max(res, currentLength);
      }

      if (count < 0) {
        count = 0;
        currentLength = 0;
      }
    }

    return res;
  }

  private int bruteforce(String s) {

    int res = 0;

    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j < s.length(); j++) {
        String sub = s.substring(i, j);

        if (isValid(sub)) {
          res = Math.max(res, sub.length());
        }
      }
    }

    return res;
  }

  private boolean isValid(String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') {
        count++;
      } else if (c == ')') {
        count--;
      }

      if (count < 0) {
        return false;
      }
    }

    return count == 0;
  }
}
