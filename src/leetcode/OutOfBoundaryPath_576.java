package leetcode;

import java.util.Arrays;

// There is an m by n grid with a ball.
//
// Given the start coordinate (i,j) of the ball,
// you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
//
// However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
// The answer may be very large, return it after mod 10^9 + 7.
public class OutOfBoundaryPath_576 {

  private static final int M = 1000000007;

  public int findPaths(int m, int n, int N, int i, int j) {
    int[][][] memo = new int[m][n][N + 1];

    for (int[][] matrix : memo) {
      for (int[] row : matrix) {
        Arrays.fill(row, -1);
      }
    }
    return dfs(m, n, N, i, j, memo);
  }

  private int dfs(int m, int n, int N, int i, int j, int[][][] memo) {
    if (i == m || j == n || i < 0 || j < 0) {
      return 1;
    }
    if (N == 0) {
      return 0;
    }
    if (memo[i][j][N] >= 0) {
      return memo[i][j][N];
    }
    memo[i][j][N] =
        ((dfs(m, n, N - 1, i - 1, j, memo) + dfs(m, n, N - 1, i + 1, j, memo)) % M
            + (dfs(m, n, N - 1, i, j - 1, memo) + dfs(m, n, N - 1, i, j + 1, memo)) % M)
            % M;
    return memo[i][j][N];
  }


  // dp(i,j) = dp(i -1, j) + dp(i + 1, j) + dp(i, j-1) + dp(i, j + 1)
  public int findPathsDP(int m, int n, int N, int x, int y) {

    int[][] dp = new int[m][n];

    dp[x][y] = 1;
    int count = 0;

    // for (int i = 0; i < m ; i++) {
    //   for (int j = 0; j < n; j++) {
    //
    //   }
    // }

    return count;
  }


}
