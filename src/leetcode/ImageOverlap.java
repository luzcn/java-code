package leetcode;

// Two images A and B are given, represented as binary, square matrices of the same size.
// (A binary matrix has only 0s and 1s as values.)
//
// We translate one image however we choose (sliding it left, right, up, or down any number of units),
// and place it on top of the other image.
//
// After, the overlap of this translation is the number of positions that have a 1 in both images.
//
// (Note also that a translation does not include any kind of rotation.)
//
// What is the largest possible overlap?
//
// Example 1:
//
// Input: A = [[1,1,0],
//             [0,1,0],
//             [0,1,0]]
//        B = [[0,0,0],
//             [0,1,1],
//             [0,0,1]]
// Output: 3
// Explanation: We slide A to right by 1 unit and down by 1 unit.
public class ImageOverlap {

    // O(n^4) time, O(n^2) space
    // for all "a" in A is 1 and for all "b" in B is 1
    // we calculate the delta and save the frequency of this delta in a 2d array
    // then find the maximum delta from this 2d array
    public int largestOverlap(int[][] A, int[][] B) {

        // count by delta
        int N = A.length;
        int[][] count = new int[2 * N + 1][2 * N + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] == 1) {
                    for (int i2 = 0; i2 < N; i2++) {
                        for (int j2 = 0; j2 < N; j2++) {
                            if (B[i2][j2] == 1) {
                                // found both A[i][j], B[i2][j2] are 1
                                // count the delta
                                count[i - i2 + N][j - j2 + N] += 1;
                            }
                        }
                    }
                }
            }
        }

        int overlap = 0;
        for (int[] row : count) {
            for (int v : row) {
                overlap = Math.max(overlap, v);
            }
        }

        return overlap;
    }
}
