package leetcode;

// A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9
// such that each row, column, and both diagonals all have the same sum.
//
// Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
//
//
//
// Example 1:
//
// Input: [[4,3,8,4],
//         [9,5,1,9],
//         [2,7,6,2]]
// Output: 1
// Explanation:
// The following subgrid is a 3 x 3 magic square:
// 438
// 951
// 276
//
// while this one is not:
// 384
// 519
// 762
//
// In total, there is only one magic square inside the given grid.
public class MagicSquaresInGrid_840 {

  private static final int M = 15;

  public int numMagicSquaresInside(int[][] grid) {

    // for 3*3, the magic constant is 15

    int m = grid.length;
    if (m == 0) {
      return 0;
    }
    int n = grid[0].length;

    int res = 0;
    for (int i = 0; i < m - 2; i++) {
      for (int j = 0; j < n - 2; j++) {

        if (isMagic(new int[][]{
            {grid[i][j], grid[i][j + 1], grid[i][j + 2]},
            {grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2]},
            {grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2]}
        })) {
          res++;
        }
      }
    }

    return res;
  }

  private boolean isMagic(int[][] grid) {

    // check diagonal
    int diagonalSum1 = 0;
    for (int i = 0; i < 3; i++) {
      if (invalidNumber(grid[i][i])) {
        return false;
      }
      diagonalSum1 += grid[i][i];
    }

    if (diagonalSum1 != M) {
      return false;
    }

    for (int i = 0; i < 3; i++) {
      int rowSum = 0;
      for (int j = 0; j < 3; j++) {

        if (invalidNumber(grid[i][j])) {
          return false;
        }

        rowSum += grid[i][j];
      }

      if (rowSum != M) {
        return false;
      }
    }

    for (int i = 0; i < 3; i++) {
      int columnSum = 0;

      for (int j = 0; j < 3; j++) {
        if (invalidNumber(grid[j][i])) {
          return false;
        }

        columnSum += grid[j][i];
      }

      if (columnSum != M) {
        return false;
      }
    }

    return true;
  }

  private boolean invalidNumber(int num) {
    return num < 1 || num > 9;
  }


  // Generate a N*N magic square
  // 3 conditions to hold:
  // 1. The position of next number is calculated by decrementing row number of previous number by 1,
  // and incrementing the column number of previous number by 1.
  //
  // At any time, if the calculated row position becomes -1, it will wrap around to n-1.
  // Similarly, if the calculated column position becomes n, it will wrap around to 0.
  //
  // 2. If the magic square already contains a number at the calculated position,
  // calculated column position will be decremented by 2, and calculated row position will be incremented by 1.
  //
  // 3. If the calculated row position is -1 & calculated column position is n, the new position would be: (0, n-2).
  public int[][] generateMagicSquare(int n) {

    int[][] grid = new int[n][n];

    int num = 1;
    // Initialize position for 1
    int i = n / 2;
    int j = n - 1;

    while (num <= n * n) {
      if (i == -1 && j == n) //3rd condition
      {
        j = n - 2;
        i = 0;
      } else {
        //1st condition helper if next number
        // goes to out of square's right side
        if (j == n) {
          j = 0;
        }

        //1st condition helper if next number is
        // goes to out of square's upper side
        if (i < 0) {
          i = n - 1;
        }
      }

      //2nd condition
      if (grid[i][j] != 0) {
        j -= 2;
        i++;
        continue;
      } else {
        //set number
        grid[i][j] = num++;
      }

      //1st condition
      j++;
      i--;
    }

    return grid;
  }
}
