package leetcode;

// Given a string s, find the longest palindromic substring in s. You may assume that the maximum
// length of s is 1000.
//
// Input: "babad"
//
// Output: "bab"
//
// Note: "aba" is also a valid answer.
///
public class LongestPalindromicSubstring_5 {

  // brute force solution takes O(n^3) time complexity
  // DP solution is O(n^2) time and O(n^2) space
  // Define dp[i][j] = true if substring s[i]...s[j] is a palindrome
  // the transition function is :
  // 1. dp[i][i] is true, a single character is always palindrome
  // 2. dp[i][i+1] is true, if s[i]==s[i+1], two continuous repeating chars
  // 3. dp[i][j] = dp[i+1][j-1] && s[i]==s[j] && j - i > 1
  public String longestPalindrome(String s) {
    if (s.isEmpty()) {
      return "";
    }

    int n = s.length();
    boolean[][] dp = new boolean[1000][1000];
    int maxLength = 0;
    int beginIndex = 0;

    // each single character is palindrome
    for (int i = 0; i < n; i++) {
      dp[i][i] = true;
    }

    // two continuous repeated characters is palindrome
    for (int i = 0; i < n - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        // this only updated the upper right half of the dp matrix,
        dp[i][i + 1] = true;
        beginIndex = i;
        maxLength = 2;
      }
    }

    // dp[i][j] == d[i+1][j-1] if s[i+1]==s[j-1]
    // we can only use half of the dp matrix
    for (int i = n - 3; i >= 0; i--) {
      for (int j = n - 1; j > i; j--) {
        if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
          dp[i][j] = true;

          if (j - i + 1 > maxLength) {
            beginIndex = i;
            maxLength = j - i + 1;
          }
        }
      }
    }

    return s.substring(beginIndex, beginIndex + maxLength);
  }

  // extend from center
  // there are 2*n - 1 center position, either the single character of the middle of two chars
  public String getLongestPalindrom(String s) {
    if (s == null) {
      return null;
    }

    int n = s.length();
    int begin = 0, maxLength = 0;

    for (int center = 0; center <= 2 * n - 1; center++) {
      int left = center / 2;
      int right = left + center % 2;

      while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
        if (right - left + 1 > maxLength) {
          begin = left;
          maxLength = right - left + 1;
        }
        left--;
        right++;
      }
    }

    return s.substring(begin, begin + maxLength);
  }
}
