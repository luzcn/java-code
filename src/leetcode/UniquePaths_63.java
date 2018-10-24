package leetcode;

// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//The robot can only move either down or right at any point in time.
// The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
// Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
// An obstacle and empty space is marked as 1 and 0 respectively in the grid.
public class UniquePaths_63 {

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {

    return uniquePathDP1(obstacleGrid);
  }

  // O(m*n) space
  private int uniquePathDP1(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
      if (obstacleGrid[i][0] == 0) {
        dp[i][0] = 1;
      } else {
        break;
      }
    }

    for (int j = 0; j < n; j++) {
      if (obstacleGrid[0][j] == 0) {
        dp[0][j] = 1;
      } else {
        break;
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {

        dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
      }
    }

    return dp[m - 1][n - 1];
  }


  // O(1) space, reuse the given grid
  private int unquePathDP2(int[][] grid) {
    int m = grid.length;
    if (m == 0) {
      return 0;
    }

    int n = grid[0].length;

    if (grid[0][0] == 1) {
      return 0;
    }

    grid[0][0] = 1;

    // Filling the values for the first column
    for (int i = 1; i < m; i++) {
      grid[i][0] = (grid[i][0] == 0 && grid[i - 1][0] == 1) ? 1 : 0;
    }

    // Filling the values for the first row
    for (int i = 1; i < n; i++) {
      grid[0][i] = (grid[0][i] == 0 && grid[0][i - 1] == 1) ? 1 : 0;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (grid[i][j] == 0) {
          grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
        } else {
          grid[i][j] = 0;
        }
      }
    }

    return grid[m - 1][n - 1];
  }
}
