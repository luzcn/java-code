package leetcode;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1->3->1->1->1 minimizes the sum.
 */

public class MinimumPathSum {

    // dp, O(m*n) space
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        if (m == 0) {
            return 0;
        }

        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }


    // O(m + n) space
    private int minPathSum2(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }

        int n = grid[0].length;
        int[] rows = new int[m];
        int[] columns = new int[n];
        rows[0] = grid[0][0];
        columns[0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            rows[i] = rows[i - 1] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            columns[j] = columns[j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                int value = grid[i][j] + Math.min(rows[i], columns[j]);
                rows[i] = value;
                columns[j] = value;
            }
        }

        return m > n ? rows[m - 1] : columns[n - 1];
    }


    // recursive solution
    private int dfs(int[][] grid, int x, int y) {
        if (x == 0 && y == 0) {
            return grid[0][0];
        }

        if (x == 0) {
            return grid[x][y] + dfs(grid, x, y - 1);
        }

        if (y == 0) {
            return grid[x][y] + dfs(grid, x - 1, y);
        }

        return grid[x][y] + Math.min(dfs(grid, x - 1, y), grid[x][y - 1]);
    }
}
