package datastructure.Array;

// given a 2d matrix, print out the numbers in diagonal order
// # Input:
// # 1 2 3
// # 4 5 6
// # 7 8 9
//
// # Print Output:
// # 3
// # 2 6
// # 1 5 9
// # 4 8
// # 7
public class MatrixPrintDiagonal {

  public void print(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
      return;
    }

    int m = grid.length;
    int n = grid[0].length;

    int i = 0;
    int j = n - 1;
    while (i < m) {
      int k = i;
      int l = j < 0 ? 0 : j;
      while (k < m && l < n) {
        System.out.print(grid[k][l] + " ");
        k++;
        l++;
      }
      System.out.println();
      j--;
      if (j < 0) {
        i++;
      }
    }
  }
}
