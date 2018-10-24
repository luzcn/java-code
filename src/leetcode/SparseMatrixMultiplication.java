package leetcode;

//Given two sparse matrices A and B, return the result of AB.
//
//You may assume that A's column number is equal to B's row number.
//
//Example:
//
//A = [
//  [ 1, 0, 0],
//  [-1, 0, 3]
//]
//
//B = [
//  [ 7, 0, 0 ],
//  [ 0, 0, 0 ],
//  [ 0, 0, 1 ]
//]
//
//
//     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
//AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                  | 0 0 1 |
public class SparseMatrixMultiplication {

  // brute force solution
  // C[i,j] = Sum(A[i,k] * B[k,j])
  public int[][] multiply(int[][] A, int[][] B) {
    if (A.length == 0 || B.length == 0) {
      return new int[0][0];
    }

    int m = A.length;
    int n = B.length;
    int p = B[0].length;

    // the result is m*n matrix
    int[][] C = new int[m][p];

    for (int i = 0; i < m; i++) {
      for (int k = 0; k < n; k++) {
        if (A[i][k] != 0) {
          for (int j = 0; j < p; j++) {
            if (B[k][j] != 0) {
              C[i][j] += A[i][k] * B[k][j];
            }
          }
        }
      }
    }

    return C;
  }
}
