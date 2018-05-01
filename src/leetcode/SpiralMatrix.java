package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * Example 1:
 *
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        while (left <= right && top <= bottom) {

            // print the top from left to right
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;

            // print the right column from top to down
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // if there is only row or one column,
            // no need to print repeat
            if (left == right || top == bottom) {
                break;
            }

            // print the bottom row, from right to left
            for (int j = right; j >= left; j--) {
                result.add(matrix[bottom][j]);
            }
            bottom--;

            // print the left column from bottom to top
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }

        return result;
    }

    // Given a positive integer n, generate a square matrix filled with elements from 1 to n*n in spiral order.
    // Example:
    //
    // Input: 3
    // Output:
    // [
    //  [ 1, 2, 3 ],
    //  [ 8, 9, 4 ],
    //  [ 7, 6, 5 ]
    // ]

    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        if (n == 0) {
            return matrix;
        }

        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;
        int number = 1;

        while (left <= right && top <= bottom) {

            // insert to top row from left to right
            for (int j = left; j <= right; j++) {
                matrix[top][j] = number++;
            }
            top++;

            // insert to the right column from top to down
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = number++;
            }
            right--;

            if (left > right || top > bottom) {
                break;
            }

            // insert to the bottom row from right to left
            for (int j = right; j >= left; j--) {
                matrix[bottom][j] = number++;
            }
            bottom--;

            // insert to the left column from bottom to top
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = number++;
            }
            left++;
        }

        return matrix;
    }

}
