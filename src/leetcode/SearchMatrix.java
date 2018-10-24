package leetcode;

// Write an efficient algorithm that searches for a value in an m x n matrix.
// This matrix has the following properties:
//
// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
//
// Example 1:
// Input:
// matrix = [
//   [1,   3,  5,  7],
//   [10, 11, 16, 20],
//   [23, 30, 34, 50]
// ]
// target = 3
// Output: true

// Thought:
// the order in rows is zigzag style,
// if we search from bottom-left and do similar binary search, it takes O(m+n)
// if we directly binary search on this matrix
public class SearchMatrix {

  // binary search
  // O(logm + logn)
  public boolean searchMatrix(int[][] matrix, int target) {

    int m = matrix.length;
    int n = matrix[0].length;

    int topRow = 0, bottomRow = m - 1;
    int leftCol = 0, rightCol = n - 1;

    while (topRow <= bottomRow) {

      // find the mid of row index
      int rowMid = topRow + (bottomRow - topRow) / 2;

      while (leftCol <= rightCol) {
        // get the mid of column index
        int colMid = leftCol + (rightCol - leftCol) / 2;

        if (matrix[rowMid][colMid] == target) {
          return true;
        } else if (target >= matrix[rowMid][leftCol] && target <= matrix[rowMid][rightCol]) {

          // if the target is in the range of this row,
          // then do binary search on this row
          // otherwise, break and try next row in binary search

          if (matrix[rowMid][colMid] > target) {
            rightCol = colMid - 1;
          } else {
            leftCol = colMid + 1;
          }
        } else {
          break;
        }
      }

      if (matrix[rowMid][leftCol] < target) {
        topRow = rowMid + 1;
      } else {
        bottomRow = rowMid - 1;
      }
    }

    return false;
  }

  // Search a 2D Matrix II
  // Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
  //
  // Integers in each row are sorted in ascending from left to right.
  // Integers in each column are sorted in ascending from top to bottom.
  // Consider the following matrix:
  //
  // [
  //   [1,   4,  7, 11, 15],
  //   [2,   5,  8, 12, 19],
  //   [3,   6,  9, 16, 22],
  //   [10, 13, 14, 17, 24],
  //   [18, 21, 23, 26, 30]
  // ]
  // O(m+n) time
  public boolean searchMatrix2(int[][] matrix, int target) {
    int m = matrix.length;
    if (m == 0) {
      return false;
    }

    int n = matrix[0].length;

    int x = m - 1;
    int y = 0;

    while (x >= 0 && y < n) {
      if (matrix[x][y] == target) {
        return true;
      } else if (matrix[x][y] < target) {
        y++;
      } else {
        x--;
      }
    }

    return false;
  }
}
