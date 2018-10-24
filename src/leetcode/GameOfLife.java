package leetcode;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular
 * automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell
 * interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four
 * rules (taken from the above Wikipedia article):
 *
 * - Any live cell with fewer than two live neighbors dies, as if caused by under-population. - Any
 * live cell with two or three live neighbors lives on to the next generation. - Any live cell with
 * more than three live neighbors dies, as if by over-population.. - Any dead cell with exactly
 * three live neighbors becomes a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board given its current
 * state.
 */

//To avoid decisions and branches in the counting loop,
//the rules can be rearranged from an egocentric approach of the inner field regarding its neighbors to a scientific observer's viewpoint:
//1. if the sum of all nine fields is 3, the inner field state for the next generation will be life (no matter of its previous contents);
//2. if the all-field sum is 4, the inner field retains its current state
//3. every other sum sets the inner field to death.
public class GameOfLife {

  // how to solve in O(1) space?
  // the input matrix are 32bit integers,
  // but the values are only 0 or 1, which can be represented in 1 bit
  // so, we can re-use the second bit to save the next state
  // 0 -> 00, the current state is dead, and next state keep dead
  // 0 -> 10, the current state is dead, next state is live
  // 1 -> 01, the current state is live, next state is dead
  // 1 -> 11, the current state is live, next state is also live
  public void gameOfLife(int[][] board) {

    m = board.length;
    if (m == 0) {
      return;
    }

    n = board[0].length;

    //1. if the sum of all nine fields is 3, => life (no matter of its previous contents);
    //2. if the all-field sum is 4, => its current state (no change)
    //3. every other sum sets the inner field to death.
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int count = computeNineCells(board, i, j);

        if (count == 3) {
          // the next state is 1, so binary representation is "10", so it is 2
          board[i][j] = board[i][j] | 2;
        } else if (count == 4) {
          if ((board[i][j] & 1) > 0) {
            board[i][j] = board[i][j] | 2;
          }
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] >>= 1;
      }
    }
  }


  private int computeNineCells(int[][] board, int x, int y) {
    int count = board[x][y] & 1;

    for (int[] dir : dirs) {
      int i = x + dir[0];
      int j = y + dir[1];

      if (i >= 0 && i < m && j >= 0 && j < n) {
        // take the last bit of each cell value,
        // which indicates the current state.
        count += (board[i][j] & 1);
      }
    }
    return count;
  }


  private int m;
  private int n;

  // eight neighbors
  private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
}
