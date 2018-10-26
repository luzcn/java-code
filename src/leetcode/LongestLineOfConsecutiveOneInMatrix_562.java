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

    return longestLineBruteForce(M);
  }

  private int longestLineBruteForce(int[][] M) {
    // construct 4 helper matrix
    int m = M.length;
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

    // anti-diagonal
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

      }
    }

    return ones;

  }
}
