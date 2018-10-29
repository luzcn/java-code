package leetcode;

// Given a 01 matrix M, find the longest line of consecutive one in the matrix.
// The line could be horizontal, vertical, diagonal or anti-diagonal.
// Example:
// Input:
// [[0,1,1,0],
//  [0,1,1,0],
//  [0,0,0,1]]
// Output: 3
// Hint: The number of elements in the given matrix will not exceed 10,000.
public class LongestLineOfConsecutiveOneInMatrix_562 {

  public int longestLine(int[][] M) {

    return longestLine3DArray(M);
  }

  // O(m*n) time, and scan only onece
  // O(m*n*4) space
  private int longestLine3DArray(int[][] M) {
    int m = M.length;
    if (m == 0) {
      return 0;
    }
    int n = M[0].length;

    int ones = 0;

    // 0: vertical
    // 1: horizontal
    // 2: diagonal
    // 3: anti-diagonal
    int[][][] dp = new int[m][n][4];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (M[i][j] == 1) {
          dp[i][j][0] = j == 0 ? 1 : dp[i][j - 1][0] + 1;
          dp[i][j][1] = i == 0 ? 1 : dp[i - 1][j][1] + 1;
          dp[i][j][2] = i == 0 || j == 0 ? 1 : dp[i - 1][j - 1][2] + 1;
          dp[i][j][3] = i == 0 || j == n - 1 ? 1 : dp[i - 1][j + 1][3] + 1;

          ones = Math.max(ones,
              Math.max(dp[i][j][0], Math.max(dp[i][j][1], Math.max(dp[i][j][2], dp[i][j][3]))));
        }
      }
    }

    return ones;
  }


  // brute force, O(m *n ) time, and need to scan the matrix 6 times
  private int longestLineBruteForce(int[][] M) {

    int m = M.length;
    if (m == 0) {
      return 0;
    }
    int n = M[0].length;

    int ones = 0;

    // vertical
    for (int i = 0; i < m; i++) {
      int count = 0;
      for (int j = 0; j < n; j++) {
        if (M[i][j] == 1) {
          count++;
        } else {
          count = 0;
        }

        ones = Math.max(ones, count);
      }
    }

    // horizontal
    int[][] gridhorizontal = new int[m][n];
    int maxInhorizontal = 0;

    for (int j = 0; j < n; j++) {
      int count = 0;
      for (int i = 0; i < m; i++) {

        if (M[i][j] == 1) {
          count++;
        } else {
          count = 0;
        }

        ones = Math.max(ones, count);
      }
    }

    // lower diagonal
    for (int i = 0; i < m; i++) {
      int count = 0;
      for (int x = i, y = 0; x < m && y < n; x++, y++) {
        if (M[x][y] == 1) {
          count++;
        } else {
          count = 0;
        }

        ones = Math.max(ones, count);
      }
    }

    // upper diagonal
    for (int i = 0; i < m; i++) {
      int count = 0;
      for (int x = 0, y = i; x < m && y < n; x++, y++) {
        if (M[x][y] == 1) {
          count++;
        } else {
          count = 0;
        }

        ones = Math.max(ones, count);
      }
    }

    // lower anti-diagonal
    for (int i = 0; i < m; i++) {
      int count = 0;
      for (int x = i, y = n - 1; x < m && y >= 0; x++, y--) {
        if (M[x][y] == 1) {
          count++;
        } else {
          count = 0;
        }

        ones = Math.max(ones, count);
      }
    }

    // upper anti-diagonal
    for (int j = n - 1; j >= 0; j--) {
      int count = 0;

      for (int x = 0, y = j; x < m && y >= 0; x++, y--) {
        if (M[x][y] == 1) {
          count++;
        } else {
          count = 0;
        }

        ones = Math.max(ones, count);
      }
    }

    return ones;

  }
}
