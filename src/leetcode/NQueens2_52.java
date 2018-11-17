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

  private int[] rows;
  private int count = 0;
  private int n;

  public int totalNQueens(int n) {

    rows = new int[n];
    this.n = n;

    dfs(0);
    return count;
  }

  private void dfs(int index) {
    if (index >= n) {
      count++;
      // print();
      // System.out.println("-----------------");
    }

    for (int value = 0; value <= n - 1; value++) {
      if (isValid(value, index)) {
        rows[index] = value;

        dfs(index + 1);
      }
    }
  }


  private boolean isValid(int value, int index) {
    // check row
    for (int i = 0; i < index; i++) {
      if (rows[i] == value) {
        return false;
      }
    }

    // check diagonal
    int x = index - 1, y = value - 1;
    while (x >= 0 && y >= 0) {
      if (rows[x] == y) {
        return false;
      }
      x--;
      y--;
    }

    // check anti-diagonal
    x = index - 1;
    y = value + 1;
    while (x >= 0 && y < n) {
      if (rows[x] == y) {
        return false;
      }

      x--;
      y++;
    }

    return true;
  }
}
