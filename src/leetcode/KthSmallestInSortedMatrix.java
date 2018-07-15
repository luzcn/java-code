package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// Given a n x n matrix where each of the rows and columns are sorted in ascending order,
// find the kth smallest element in the matrix.
//
// Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
// Example:
//
// matrix = [
//    [ 1,  5,  9],
//    [10, 11, 13],
//    [12, 13, 15]
// ],
// k = 8,
//
// return 13.
public class KthSmallestInSortedMatrix {


    private int countEqaulOrLess(int[][] matrix, int target) {
        int x = matrix.length - 1;
        int y = 0;
        int count = 0;

        while (x >= 0 && y < matrix[0].length) {
            int value = matrix[x][y];

            if (value <= target) {
                // need to move y to right index
                y++;

                // all x + 1 elements are less than targets
                count += x + 1;
            } else {
                x--;
            }
        }

        return count;
    }

    public int kthSmallest(int[][] matrix, int k) {

        // binary search

        if (matrix.length == 0 || k <= 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        if (k == 1) {
            return matrix[0][0];
        }

        int min = matrix[0][0];
        int max = matrix[m - 1][n - 1];

        while (min <= max) {
            int mid = min + (max - min) / 2;

            int count = countEqaulOrLess(matrix, mid);

            // don't return any numbers yet, if we found k == count
            // we need to set max = mid - 1 and keep search
            // until min == max, thus we can guarantee the value "mid" is in matrix

            if (count < k) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return min;
    }


    // max heap
    public int kthSmallestHeap(int[][] matrix, int k) {
        if (matrix.length == 0 || k <= 0) {
            return 0;
        }

        // the heap stores an array with 3 elements: value, x and y index
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);

        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxHeap.add(matrix[i][j]);

                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }

        return maxHeap.peek();
    }
}
