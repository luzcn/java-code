package leetcode;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * For example, X X X X X O O X X X O X X O X X After running your function, the board should be:
 *
 * X X X X X X X X X X X X X O X X
 *
 * Thoughts: dfs + backtracking
 */
public class SurroundedRegions {

  private static final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  private void dfs(char[][] board, int x, int y) {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
      return;
    }

    if (board[x][y] != 'O') {
      return;
    }

    board[x][y] = '2';

    for (int[] dir : dirs) {
      dfs(board, x + dir[0], y + dir[1]);
    }
  }

  public void solve(char[][] board) {
    if (board.length == 0) {
      return;
    }

    int m = board.length;
    int n = board[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // first and last columns
        // first and last rows
        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
          if (board[i][j] == 'O') {
            dfs(board, i, j);
          }
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }

        if (board[i][j] == '2') {
          board[i][j] = 'O';
        }
      }
    }
  }
}
