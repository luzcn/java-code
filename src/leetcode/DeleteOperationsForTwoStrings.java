package leetcode;

/**
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and
 * word2 the same, where in each step you can delete one character in either string.
 *
 * Example 1: Input: "sea", "eat" Output: 2 Explanation: You need one step to make "sea" to "ea" and
 * another step to make "eat" to "ea". Note: The length of given words won't exceed 500. Characters
 * in given words can only be lower-case letters.
 */
public class DeleteOperationsForTwoStrings {

  public int minDistance(String s, String t) {
    // dp idea, similar to EditDistance
    int m = s.length(), n = t.length();
    int[][] dp = new int[m + 1][n + 1];

    // for (int i = 0; i <= m; i++) {
    //     dp[i][0] = i;
    // }
    //
    // for (int j = 0; j <= n; j++) {
    //     dp[0][j] = j;
    // }

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = i + j;
        } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
        }
      }
    }

    return dp[m][n];
  }

  // use 1-d array
  // create a temp array with t.length() + 1
  // so we can map
  // dp[i-1][j] -> dp[j]
  // dp[i-1][j-1] -> dp[j-1]
  // dp[i][j-1] -> temp[j-1]
  private int minDistance2(String s, String t) {
    int m = s.length(), n = t.length();
    int[] dp = new int[n + 1];

    for (int i = 0; i <= m; i++) {
      int[] temp = new int[n + 1];

      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          temp[j] = i + j;
        } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
          temp[j] = dp[j - 1];
        } else {
          temp[j] = Math.min(dp[j], temp[j - 1]) + 1;
        }
      }
      dp = temp;
    }

    return dp[n];
  }
}
