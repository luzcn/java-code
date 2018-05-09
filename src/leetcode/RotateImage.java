package leetcode;

// You are given an n x n 2D matrix representing an image.
//
// Rotate the image by 90 degrees (clockwise).
//
// Note:
//
// You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
//
// Example 1:
//
// Given input matrix =
// [
//   [1,2,3],
//   [4,5,6],
//   [7,8,9]
// ],
//
// rotate the input matrix in-place such that it becomes:
// [
//   [7,4,1],
//   [8,5,2],
//   [9,6,3]
// ]
// Example 2:
//
// Given input matrix =
// [
//   [ 5, 1, 9,11],
//   [ 2, 4, 8,10],
//   [13, 3, 6, 7],
//   [15,14,12,16]
// ],
//
// rotate the input matrix in-place such that it becomes:
// [
//   [15,13, 2, 5],
//   [14, 3, 4, 1],
//   [12, 6, 8, 9],
//   [16, 7,10,11]
// ]
public class RotateImage {

    // from the example, we can see position transition
    // (0,0) -> (0,2) -> (2,2) -> (2,0) -> (0,0)
    // we set two variables: row and column to indicate current processing row and column index
    // for each elements we need to perform 3 transitions
    //
    // m[n-1-j][i] =>  m[i][j] => m[j][n-1-i] => m[n-1-i][n-1-i] => m[n-1-j][i]
    public void rotate(int[][] matrix) {

        int n = matrix.length;

        int row = 0;
        int column = 0;

        while (n > 1) {
            for (int i = row; i < n - row - 1; i++) {
                for (int j = column; j < n - column - 1; j++) {
                    int temp1 = matrix[i][j];
                    matrix[i][j] = matrix[n - 1 - j][i];

                    int temp2 = matrix[j][n - 1 - i];
                    matrix[j][n - 1 - i] = temp1;

                    int temp3 = matrix[n - 1 - i][n - 1 - j];
                    matrix[n - 1 - i][n - 1 - j] = temp2;

                    matrix[n - 1 - j][i] = temp3;
                }
                column++;
            }
            row++;
            n--;
        }
    }
}
