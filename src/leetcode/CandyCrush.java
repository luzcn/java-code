package leetcode;

// This question is about implementing a basic elimination algorithm for Candy Crush.
//
// Given a 2D integer array board representing the grid of candy,
// different positive integers board[i][j] represent different types of candies.
//
// A value of board[i][j] = 0 represents that the cell at position (i, j) is empty.
// The given board represents the state of the game following the player's move.
//
// Now, you need to restore the board to a stable state by crushing candies according to the following rules:
//
// - If three or more candies of the same type are adjacent vertically or horizontally,
// "crush" them all at the same time - these positions become empty.
//
// - After crushing all candies simultaneously, if an empty space on the board has candies on top of itself,
// then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
//
// - After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
//
// - If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
public class CandyCrush {

  // 1. use an auxiliary 2d array to mark boar[i][j] need to crush,
  // this can avoid accidentally crush candy that is part of another row.
  // 123
  // 145
  // 111
  // if we crush the first column, the last row will be invalid to crush
  //
  // 2. for each row or column, we use a "sliding window" to find contiguous segments of the same character.
  // if any of these segments have length 3 or more, mark them as crush.

  public int[][] candyCrush(int[][] board) {

    int m = board.length;
    int n = board[0].length;

    boolean[][] crushed = new boolean[m][n];
    boolean finished = true;

    // check each columns
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n - 2; j++) {
        int candy = board[i][j];
        if (candy != 0 && candy == board[i][j + 1] && candy == board[i][j + 2]) {
          crushed[i][j] = true;
          crushed[i][j + 1] = true;
          crushed[i][j + 2] = true;

          // need to crush and gravity
          // so set finished flag to false
          finished = false;
        }
      }
    }

    // check each row
    for (int j = 0; j < n; j++) {
      for (int i = 0; i < m - 2; i++) {
        int candy = board[i][j];

        if (candy != 0 && candy == board[i + 1][j] && candy == board[i + 2][j]) {
          crushed[i][j] = true;
          crushed[i + 1][j] = true;
          crushed[i + 2][j] = true;

          finished = false;
        }
      }
    }

    // gravity
    // for each column, check all the rows
    for (int j = 0; j < n; j++) {
      int rowToCrush = m - 1;

      // if board[i][j] is marked as crush
      // skip it, move "i" up until we find a candy need to move gravity, then copy to the board[rowToCrush][j]
      for (int i = m - 1; i >= 0; i--) {
        if (!crushed[i][j]) {
          board[rowToCrush--][j] = board[i][j];
        }
      }

      while (rowToCrush >= 0) {
        board[rowToCrush--][j] = 0;
      }
    }

    return finished ? board : candyCrush(board);
  }
}
