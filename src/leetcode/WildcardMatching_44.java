package leetcode;

// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
//
// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
// The matching should cover the entire input string (not partial).
//
// Note:
//
// s could be empty and contains only lowercase letters a-z.
// p could be empty and contains only lowercase letters a-z, and characters like ? or *.
// Example 1:
//
// Input:
// s = "aa"
// p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".
// Example 2:
//
// Input:
// s = "aa"
// p = "*"
// Output: true
// Explanation: '*' matches any sequence.
// Example 3:
//
// Input:
// s = "cb"
// p = "?a"
// Output: false
// Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
// Example 4:
//
// Input:
// s = "adceb"
// p = "*a*b"
// Output: true
// Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
// Example 5:
//
// Input:
// s = "acdcb"
// p = "a*c?b"
// Output: false
public class WildcardMatching_44 {

  // greedy solution
  // whenever encounter ‘*’ in p, keep record of the current position of ‘*’ in p and the current index in s.
  // Try to match the stuff behind this ‘*’ in p with s, if not matched, just s++ and then try to match again.
  public boolean isMatch(String s, String p) {
    int i = 0;
    int j = 0;

    // the position of * in p
    int star = -1;

    // the position of matching * in s
    int mark = -1;

    while (i < s.length()) {

      if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
        i++;
        j++;
      } else if (j < p.length() && p.charAt(j) == '*') {
        star = j;
        mark = i;

        // start using * as "" first
        j++;
      } else if (star != -1) {
        j = star + 1;

        // use * to match s[i]
        i = ++mark;
      } else {
        return false;
      }

    }

    // remove redundant '*' in p.
    while (j < p.length() && p.charAt(j) == '*') {
      j++;
    }

    return j == p.length();
  }


  public boolean isMatchDP(String s, String p) {
    int m = s.length();
    int n = p.length();

    boolean[][] dp = new boolean[m + 1][n + 1];
    // empty string can match
    dp[0][0] = true;

    for (int i = 1; i <= n; i++) {
      if (p.charAt(i - 1) == '*') {
        dp[0][i] = dp[0][i - 1];
      }
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (p.charAt(j - 1) == '*') {
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        } else if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
          dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }

    return dp[m][n];
  }
}
