package datastructure.DP;

// Longest common sub sequence
// given two sting s and t, find the longest sequence which has the same characters in s and t
// return the length
public class LongestCommonSubSequence {

  // O(m*n) time, O(m*n) space
  public int lcs(String s, String t) {
    if (s.isEmpty() || t.isEmpty()) {
      return 0;
    }

    int m = s.length();
    int n = t.length();

    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
          // set to 0 if find the longest common sub string
        }
      }
    }

    // to print out the common sequence, we use backtrack
    // backtrack
    StringBuilder res = new StringBuilder();
    int x = m;
    int y = n;

    while (x > 0 && y > 0) {
      if (dp[x][y] == dp[x][y - 1]) {
        y--;
      } else if (dp[x][y] == dp[x - 1][y]) {
        x--;
      } else {
        res.append(s.charAt(x - 1));
        x--;
        y--;
      }
    }

    System.out.println(res.reverse().toString());

    return dp[m][n];
  }

}
