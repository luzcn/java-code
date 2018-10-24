package leetcode;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * Empty cells are indicated by the character '.'.
 *
 * You may assume that there will be only one unique solution.
 *
 * Thought: dfs + backtracking
 */
public class SudokuSolver {

  private boolean isValid(char[][] board, int x, int y, char v) {
    int m = board.length;
    int n = board[0].length;

    // check row
    for (int i = 0; i < m; i++) {
      if (board[i][y] == v) {
        return false;
      }
    }

    // check column
    for (int j = 0; j < n; j++) {
      if (board[x][j] == v) {
        return false;
      }
    }

    // check 3*3 squre
    int squareX = (x / 3) * 3;
    int squareY = (y / 3) * 3;

    for (int i = 0; i <= 2; i++) {
      for (int j = 0; j <= 2; j++) {
        if (board[i][y] != '.' && board[squareX + i][squareY + j] == v) {
          return false;
        }
      }
    }

    return true;
  }


  private boolean dfs(char[][] board, int row, int column) {
    if (column >= 9) {
      return dfs(board, row + 1, 0);
    }

    if (row == 9) {
      return true;
    }

    if (board[row][column] != '.') {
      return dfs(board, row, column + 1);
    }

    for (char v = '1'; v <= '9'; v++) {
      if (isValid(board, row, column, v)) {
        board[row][column] = v;
        if (dfs(board, row, column + 1)) {
          return true;
        }

        board[row][column] = '.';
      }
    }

    return false;
  }

  public void solveSudoku(char[][] board) {
    dfs(board, 0, 0);
  }
}