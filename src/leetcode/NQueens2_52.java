package leetcode;

// The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
// Given an integer n, return the number of distinct solutions to the n-queens puzzle.

// Example:
//
// Input: 4
// Output: 2
// Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
// [
//  [".Q..",  // Solution 1
//   "...Q",
//   "Q...",
//   "..Q."],
//
//  ["..Q.",  // Solution 2
//   "Q...",
//   "...Q",
//   ".Q.."]
// ]
public class NQueens2_52 {
  public int totalNQueens(int n) {

    int[][] board = new int[n][n];



  }


  private boolean isValid(int[][] board, int x, int y) {
    // check row
    for (int j= 0; j < y; j++ ) {
      if (board[0][j] == 1) {
        return false;
      }
    }



    // check column
    for (int i = 0; i < x; i++) {
      if ()
    }

    return true;
  }
}
