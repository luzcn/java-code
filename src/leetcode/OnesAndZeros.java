package leetcode;

// In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
//
// For now, suppose you are a dominator of m 0s and n 1s respectively.
// On the other hand, there is an array with strings consisting of only 0s and 1s.
//
// Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s.
// Each 0 and 1 can be used at most once.
//
// Example 1:
// Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
// Output: 4
// Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
//
// Example 2:
// Input: Array = {"10", "0", "1"}, m = 1, n = 1
// Output: 2
// Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
public class OnesAndZeros {

  // dfs + memo
  // for each string, if the current (m,n) can form the string, we can choose
  // 1. use it ans1 = dfs(m-c0, n-c1, index + 1) + 1
  // 2. not use it ans2 = dfs(m,n, index+1)
  // if the (m,n) cannot form the string, we can only choose to not using it.
  private int dfs(String[] strs, int m, int n, int index, int[][][] dp) {
    if (m < 0 || n < 0) {
      return 0;
    }

    if (index >= strs.length) {
      return 0;
    }
    if (dp[index][m][n] > 0) {
      return dp[index][m][n];
    }

    int count0 = 0;
    int count1 = 0;
    for (char c : strs[index].toCharArray()) {
      if (c == '0') {
        count0++;
      } else {
        count1++;
      }
    }

    int ansTake = 0;
    if (count0 <= m && count1 <= n) {
      ansTake = dfs(strs, m - count0, n - count1, index + 1, dp) + 1;
    }

    int ansNotTake = dfs(strs, m, n, index + 1, dp);

    dp[index][m][n] = Math.max(ansTake, ansNotTake);
    return Math.max(ansTake, ansNotTake);
  }


  private int findMaxFormDP(String[] strs, int m, int n) {
    if (m < 0 || n < 0) {
      return 0;
    }

    int[][] dp = new int[m + 1][n + 1];

    for (String s : strs) {
      int count0 = 0;
      int count1 = 0;
      for (char c : s.toCharArray()) {
        if (c == '0') {
          count0++;
        } else {
          count1++;
        }
      }

      for (int zeros = m; zeros >= count0; zeros--) {
        for (int ones = n; ones >= count1; ones--) {

          dp[zeros][ones] = Math.max(dp[zeros][ones], dp[zeros - count0][ones - count1] + 1);
        }
      }

    }
    return dp[m][n];
  }


  public int findMaxForm(String[] strs, int m, int n) {

    if (m < 0 || n < 0) {
      return 0;
    }

    int[][][] dp = new int[strs.length][m + 1][n + 1];
    return dfs(strs, m, n, 0, dp);
  }
}
