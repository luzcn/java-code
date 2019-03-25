package careercup.Minimax;

import java.util.*;

// You are playing the following Nim Game with your friend:
// - There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones.
// - The one who removes the last stone will be the winner.
// - You will take the first turn to remove the stones.
//
// Both of you are very clever and have optimal strategies for the game.
// Write a function to determine whether you can win the game given the number of stones in the heap.
public class NimGame {

  public boolean canWin(int n) {
    // from observation, we find

    if (n <= 0) {
      return false;
    }

    return n % 4 != 0;

  }


  // minmax, backtracking
  private boolean canWinDFS(int n) {
    if (n <= 0) {
      return false;
    }

    if (n == 1 || n == 2 || n == 3) {
      return true;
    }

    return !(canWinDFS(n - 1) && canWinDFS(n - 2) && canWinDFS(n - 3));
  }

}
