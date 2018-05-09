package leetcode;

import java.util.Arrays;

/**
 * In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0.
 *
 * What is the largest axis-aligned plus sign of 1s contained in the grid?
 * Return the order of the plus sign. If there is none, return 0.
 *
 * An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1
 * going up, down, left, and right, and made of 1s. This is demonstrated in the diagrams below.
 *
 * Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.
 *
 * Examples of Axis-Aligned Plus Signs of Order k:
 *
 * Order 1:
 * 000
 * 010
 * 000
 *
 * Order 2:
 * 00000
 * 00100
 * 01110
 * 00100
 * 00000
 *
 * Order 3:
 * 0000000
 * 0001000
 * 0001000
 * 0111110
 * 0001000
 * 0001000
 * 0000000
 * Example 1:
 *
 * Input: N = 5, mines = [[4, 2]]
 * Output: 2
 * Explanation:
 * 11111
 * 11111
 * 11111
 * 11111
 * 11011
 * In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.
 * Example 2:
 *
 * Input: N = 2, mines = []
 * Output: 1
 * Explanation:
 * There is no plus sign of order 2, but there is of order 1.
 * Example 3:
 *
 * Input: N = 1, mines = [[0, 0]]
 * Output: 0
 * Explanation:
 * There is no plus sign, so return 0.
 */
public class LargestPlusSign {

    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N == 0) {
            return 0;
        }

        // build the matrix
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], 1);
        }

        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]] = 0;
        }

        int k = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (matrix[i][j] == 1) {
                    int up = 1;
                    for (int upIndex = i - 1; upIndex >= 0; upIndex--) {
                        if (matrix[upIndex][j] == 0) {
                            break;
                        }

                        up++;
                    }

                    int down = 1;
                    for (int downIndex = i + 1; downIndex < N; downIndex++) {
                        if (matrix[downIndex][j] == 0) {
                            break;
                        }

                        down++;
                    }

                    int left = 1;
                    for (int leftIndex = j - 1; leftIndex >= 0; leftIndex--) {
                        if (matrix[i][leftIndex] == 0) {
                            break;
                        }

                        left++;
                    }

                    int right = 1;
                    for (int rightIndex = j - 1; rightIndex < N; rightIndex++) {
                        if (matrix[i][rightIndex] == 0) {
                            break;
                        }

                        right++;
                    }

                    k = Math.max(k, Math.min(Math.min(up, down), Math.min(left, right)));
                }

            }
        }
        return k;
    }
}
