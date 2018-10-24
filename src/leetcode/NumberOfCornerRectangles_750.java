package leetcode;

// Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
//
// A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle.
// - Note that only the corners need to have the value 1.
// - Also, all four 1s used must be distinct.
//
//
//
// Example 1:
//
// Input: grid =
// [[1, 0, 0, 1, 0],
//  [0, 0, 1, 0, 1],
//  [0, 0, 0, 1, 0],
//  [1, 0, 1, 0, 1]]
// Output: 1
// Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
//
//
// Example 2:
//
// Input: grid =
// [[1, 1, 1],
//  [1, 1, 1],
//  [1, 1, 1]]
// Output: 9
// Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
//
//
// Example 3:
//
// Input: grid =
// [[1, 1, 1, 1]]
// Output: 0
// Explanation: Rectangles must have four distinct corners.
//
// Note:
// - The number of rows and columns of grid will each be in the range [1, 200].
// - Each grid[i][j] will be either 0 or 1.
// - The number of 1s in the grid will be at most 6000.
public class NumberOfCornerRectangles_750 {

  // brute force solution
  // O(n^4) time
  public int countCornerRectangles(int[][] grid) {
    int m = grid.length;
    if (m == 0) {
      return 0;
    }
    int n = grid[0].length;

    int res = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (grid[i][j] != 1) {
          continue;
        }

        for (int k = i + 1; k < m; k++) {
          for (int l = j + 1; l < n; l++) {
            if (grid[k][j] == 1 && grid[i][l] == 1 && grid[k][l] == 1) {
              res++;
            }
          }
        }
      }
    }

    return res;
  }

  // scan two rows, if grid[i][k] == grid[j][k] == 1, increase the counter
  // then, we can see these two row can form count*(count-1)/2 rectangles
  public int countCornerRectangles2(int[][] grid) {

    int m = grid.length;
    if (m == 0) {
      return 0;
    }
    int n = grid[0].length;

    int res = 0;
    for (int i = 0; i < m; i++) {
      for (int j = i + 1; j < m; j++) {
        int count = 0;

        for (int k = 0; k < n; k++) {
          if (grid[i][k] == 1 && grid[j][k] == 1) {
            count++;
          }
        }
        res += count * (count - 1) / 2;
      }
    }

    return res;
  }
}
