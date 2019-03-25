package careercup.Minimax;

import java.util.*;

// implement a tic-tac-toe minmax algorithm
// which can always win or draw
public class TicTacToe {

  // A description for the algorithm, assuming X is the "turn taking player," would look something like:
  //
  // - If the game is over, return the score from X's perspective.
  //  Otherwise get a list of new game states for every possible move
  // - Create a scores list
  // - For each of these states add the minimax result of that state to the scores list
  // - If it's X's turn, return the maximum score from the scores list
  // - If it's O's turn, return the minimum score from the scores list

  private void move(int player) {


    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {

      }
    }

  }


  private int[][] board = new int[3][3];

  private int score(int x, int y, int player) {

    if (board[x][y] == 0) {
      board[x][y] = player;
    }

    if (win(player)) {
      return player;
    }

    return 0;
  }


  private boolean win(int p) {
    int n = this.board.length;

    for (int i = 0; i < n; i++) {
      boolean isRowValid = true;
      for (int j = 0; j < n; j++) {
        if (board[i][j] != p) {
          isRowValid = false;
        }
      }
      if (isRowValid) {
        return true;
      }
    }

    for (int j = 0; j < n; j++) {
      boolean isColumn = true;
      for (int i = 0; i < n; i++) {
        if (board[i][j] != p) {
          isColumn = false;
        }
      }
      if (isColumn) {
        return true;
      }
    }

    boolean isValid = true;
    for (int i = 0; i < n; i++) {
      if (board[i][i] != p) {
        isValid = false;
        break;
      }
    }
    if (isValid) {
      return true;
    }

    isValid = true;
    for (int i = 0; i < n; i++) {
      if (board[i][n - 1 - i] != p) {
        isValid = false;
        break;
      }
    }

    return isValid;
  }

}
