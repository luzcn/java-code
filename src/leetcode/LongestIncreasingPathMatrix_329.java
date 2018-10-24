package leetcode;

// Given an integer matrix, find the length of the longest increasing path.
//
// From each cell, you can either move to four directions: left, right, up or down.
//
// You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//
// Example 1:
//
// Input: nums =
// [
//   [9,9,4],
//   [6,6,8],
//   [2,1,1]
// ]
// Output: 4
// Explanation: The longest increasing path is [1, 2, 6, 9].
// Example 2:
//
// Input: nums =
// [
//   [3,4,5],
//   [3,2,6],
//   [2,2,1]
// ]
// Output: 4
// Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
public class LongestIncreasingPathMatrix_329 {

  // dfs + memoization

  private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  private int dfs(int[][] matrix, int i, int j, int[][] memo) {

    if (memo[i][j] > 0) {
      return memo[i][j];
    }

    for (int[] dir : dirs) {
      int x = i + dir[0];
      int y = j + dir[1];

      if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length
          || matrix[x][y] <= matrix[i][j]) {
        continue;
      }

      memo[i][j] = Math.max(memo[i][j], dfs(matrix, x, y, memo));
    }

    return ++memo[i][j];
  }

  public int longestIncreasingPath(int[][] matrix) {

    int m = matrix.length;
    if (m == 0) {
      return 0;
    }
    int n = matrix[0].length;
    int res = 0;
    int[][] memo = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        res = Math.max(res, dfs(matrix, i, j, memo));
      }
    }

    return res;
  }
}
