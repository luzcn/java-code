package leetcode;

public class MaximalRectangle_85 {

  // Use DP idea, pre-process the input matrix
  // - Create a 2D table with the same size of input matrix.
  // - For each row of input matrix, count how many contiguous 1 and saved in the table
  // - For each matrix cell, matrix[i][j] == 1, keep searching the upper rows for (int k = i, k >= 0, k--).
  //
  // - Record the smallest value in the table, until the cell is 0. The local max area is (i -k + 1)* length.
  // O(n^3) time
  public int maximalRectangle(char[][] matrix) {
    if (matrix.length == 0) {
      return 0;
    }

    int m = matrix.length;
    int n = matrix[0].length;

    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == '1') {
          dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
        }
      }
    }

    int result = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == '1') {
          int length = dp[i][j];
          for (int k = i; k >= 0; k--) {
            if (dp[k][j] == 0) {
              break;
            }
            // the length should be the smallest number in the pre-processed table
            length = Math.min(length, dp[k][j]);

            // compute the max rectangle area.
            result = Math.max(result, (i - k + 1) * length);
          }

        }
      }
    }

    return result;
  }


  // Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
  //
  // For example, given the following matrix:
  //
  // 1 0 1 0 0
  // 1 0 1 1 1
  // 1 1 1 1 1
  // 1 0 0 1 0
  // Return 4.
  // we can use the similar idea of maximal rectangle
  // when processing the dp table, we need to check dp[i-1,j], dp[i-1,j-1], dp[i, j-1], and get the min value
  public int maximalSquare(char[][] matrix) {
    if (matrix.length == 0) {
      return 0;
    }

    int m = matrix.length;
    int n = matrix[0].length;

    int[][] dp = new int[m + 1][n + 1];
    int result = 0;

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (matrix[i - 1][j - 1] == '1') {
          // since it requires square, we need to check (i-1,j), (i-1,j-1) and (i, j-1)
          dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;

          result = Math.max(result, dp[i][j]);
        }
      }
    }

    return result * result;
  }
}
