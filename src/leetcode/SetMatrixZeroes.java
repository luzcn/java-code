package leetcode;

import java.util.Arrays;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Example 1:
 *
 * Input: [ [1,1,1], [1,0,1], [1,1,1] ] Output: [ [1,0,1], [0,0,0], [1,0,1] ]
 */
public class SetMatrixZeroes {

  // O(m*n) time, O(m+n) space
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    if (m == 0) {
      return;
    }

    int n = matrix[0].length;

    int[] rows = new int[m];
    int[] columns = new int[n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          rows[i] = 1;
          columns[j] = 1;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rows[i] == 1 || columns[j] == 1) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  // O(1) space, the idea is reuse the first row and first column to save the "0"s position
  public void setZersNoExtraSpace(int[][] matrix) {

    int m = matrix.length;
    if (m == 0) {
      return;
    }

    int n = matrix[0].length;

    // first we need to check if need to set first row and first column to zeros
    boolean setFirstRowZero = false;
    boolean setFirstColumnZero = false;

    for (int[] row : matrix) {
      if (row[0] == 0) {
        setFirstColumnZero = true;
        break;
      }
    }

    setFirstRowZero = Arrays.stream(matrix[0]).anyMatch(x -> x == 0);

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix[i][j] == 0) {
          matrix[0][j] = 0;
          matrix[i][0] = 0;
        }
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix[0][j] == 0 || matrix[i][0] == 0) {
          matrix[i][j] = 0;
        }
      }
    }

    if (setFirstColumnZero) {
      for (int[] row : matrix) {
        row[0] = 0;
      }
    }

    if (setFirstRowZero) {
      Arrays.fill(matrix[0], 0);
    }
  }
}
