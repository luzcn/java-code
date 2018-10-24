package leetcode;

import java.util.ArrayList;
import java.util.List;

// Given a matrix of M x N elements (M rows, N columns),
// return all elements of the matrix in diagonal order as shown in the below image.
//
// Example:
// Input:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// Output:  [1,2,4,7,5,3,6,8,9]
// Explanation:
public class DiagonalTraverse_498 {

  public List<Integer> findDiagonalOrder(int[][] matrix) {

    if (matrix.length == 0 || matrix[0].length == 0) {
      return null;
    }

    List<Integer> res = new ArrayList<>();

    int m = matrix.length;
    int n = matrix[0].length;
    boolean up = true;

    int i = 0;
    int j = 0;
    int count = m * n;

    while (count > 0) {

      if (up) {
      }

      up = !up;
    }
    return res;
  }

}
