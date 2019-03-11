package leetcode;

// Given a 2D integer matrix M representing the gray scale of an image,
// you need to design a smoother to make the gray scale of each cell
// becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself.
//
// If a cell has less than 8 surrounding cells, then use as many as you can.
//
// Example 1:
// Input:
// [[1,1,1],
//  [1,0,1],
//  [1,1,1]]
// Output:
// [[0, 0, 0],
//  [0, 0, 0],
//  [0, 0, 0]]
// Explanation:
// For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
// For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
// For the point (1,1): floor(8/9) = floor(0.88888889) = 0
// Note:
// The value in the given matrix is in the range of [0, 255].
// The length and width of the given matrix are in the range of [1, 150].
public class ImageSmoother_661 {

  public int[][] imageSmoother(int[][] M) {

    nums = M;
    m = M.length;
    if (m == 0) {
      return M;
    }

    n = M[0].length;

    int[][] res = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        res[i][j] = getAverage(i, j);
      }
    }

    return res;
  }

  private int m;
  private int n;
  private int[][] nums;
  private final int[][] dirs = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1},
      {1, 1}};

  private int getAverage(int x, int y) {
    int sum = nums[x][y];
    int count = 1;

    for (int[] dir : dirs) {
      int i = x + dir[0];
      int j = y + dir[1];
      if (i < 0 || i >= m || j < 0 || j >= n) {
        continue;
      }

      sum += nums[i][j];
      count++;
    }
    return sum / count;
  }

}
