package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
//
// Example 1:
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
// Output: true
// Example 2:
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
// Output: false
public class InterleavingString_97 {

  // 只要是遇到字符串的子序列或是匹配问题直接就上动态规划Dynamic Programming
  // 一般来说字符串匹配问题都是更新一个二维dp数组
  // dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i - 1 + j]) || (dp[i][j - 1] && s2[j - 1] == s3[j - 1 + i]);
  public boolean isInterleave(String s1, String s2, String s3) {

    // dp solution

    int m = s1.length();
    int n = s2.length();

    boolean[][] dp = new boolean[m + 1][n + 1];

    // base condition
    // s1, s2 are both empty, we can always interleave two empty strings
    dp[0][0] = true;

    // first column
    for (int i = 1; i <= m; i++) {
      dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
    }

    // first row
    for (int j = 1; j <= n; j++) {
      dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1 + j)) || (dp[i][j - 1]
            && s2.charAt(j - 1) == s3.charAt(j - 1 + i));
      }
    }

    return dp[m][n];
  }

  private boolean isInterleaveRec(String s1, String s2, String s3, int i, int j, int k) {

    if (k >= s3.length()) {
      return true;
    }

    if (i < s1.length() && s1.charAt(i) == s3.charAt(k) && j < s2.length() && s2.charAt(j) == s3
        .charAt(k)) {
      return isInterleaveRec(s1, s2, s3, i + 1, j, k + 1) || isInterleaveRec(s1, s2, s3, i, j + 1,
          k + 1);
    }

    if (i < s2.length() && s1.charAt(i) == s3.charAt(k)) {
      return isInterleaveRec(s1, s2, s3, i + 1, j, k + 1);
    }

    if (j < s3.length() && s2.charAt(j) == s3.charAt(k)) {
      return isInterleaveRec(s1, s2, s3, i, j + 1, k + 1);
    }

    return false;
  }


  // Get All Interleaving Strings
  public List<String> interleaving(String s1, String s2) {
    List<String> res = new ArrayList<>();

    getAllInterleaving(s1, s2, 0, res);
    return res;
  }

  private void getAllInterleaving(String s1, String s2, int index, List<String> res) {
    if (s2.isEmpty()) {
      res.add(s1);
      return;
    }

    for (int i = index; i <= s1.length(); i++) {
      String prefix = s1.substring(0, i);
      String suffix = s1.substring(i);

      char first = s2.charAt(0);

      String s = prefix + first + suffix;
      getAllInterleaving(s, s2.substring(1), i + 1, res);
    }
  }
}
