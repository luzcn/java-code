package leetcode;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is
 * surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 *
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1: 11110 11010 11000 00000 Answer: 1
 *
 * Example 2: 11000 11000 00100 00011 Answer: 3
 */
public class NumberOfIslands {

  private void dfs(char[][] grid, int x, int y) {

    // not valid [x,y] index
    if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
      return;
    }

    if (visited[x][y]) {
      return;
    }

    if (grid[x][y] != '1') {
      return;
    }

    visited[x][y] = true;

    for (int[] dir : dirs) {
      dfs(grid, x + dir[0], y + dir[1]);
    }
  }

  private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  private boolean[][] visited;

  // dfs
  public int countIslands(char[][] grid) {
    int count = 0;
    int m = grid.length;
    int n = grid[0].length;
    visited = new boolean[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (!visited[i][j] && grid[i][j] == '1') {
          count++;
          dfs(grid, i, j);
        }
      }
    }

    return count;
  }

  // follow up, if the bord is large, cannot fit in memory
  // thought: we can load the bord row by row and use union-find
  public int countIslands2(char[][] grid) {
    int count = 0;
    int m = grid.length;
    int n = grid[0].length;

    return count;
  }

}
