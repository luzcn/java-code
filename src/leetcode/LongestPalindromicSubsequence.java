package leetcode;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the
 * maximum length of s is 1000.
 *
 * Example 1: Input:
 *
 * "bbbab" Output: 4 One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2: Input:
 *
 * "cbbd" Output: 2 One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {

  // dp solution
  // initial condition dp[i][i] = 1
  // dp[i][j] = dp[i+1][j-1] + 2, if s[i]==s[j]
  // else dp[i][j] = max(dp[i+1][j], dp[i][j-1])
  public int getPalindromeSubsequence(String s) {

    if (s.isEmpty()) {
      return 0;
    }

    int n = s.length();
    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
    }

    // need to compare the two continuous chars
    for (int i = n - 2; i >= 0; i--) {
      for (int j = i + 1; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }

    for (int[] arr : dp) {
      for (int a : arr) {
        System.out.print(a + " ");
      }
      System.out.println();
    }
    return dp[0][dp[0].length - 1];
  }
}
