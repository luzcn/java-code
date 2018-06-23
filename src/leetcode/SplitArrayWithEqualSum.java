package leetcode;

import java.util.*;

// Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
//
//  - 0 < i, i + 1 < j, j + 1 < k < n - 1
//  - Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
//
// where we define that subarray (L, R) represents a slice of the original array starting
// from the element indexed L to the element indexed R.
//
// Example:
// Input: [1,2,1,2,1,2,1]
// Output: True
// Explanation:
// i = 1, j = 3, k = 5.
// sum(0, i - 1) = sum(0, 0) = 1
// sum(i + 1, j - 1) = sum(2, 2) = 1
// sum(j + 1, k - 1) = sum(4, 4) = 1
// sum(k + 1, n - 1) = sum(6, 6) = 1
public class SplitArrayWithEqualSum {

    // brute force solution
    public boolean splitArray(int[] nums) {

        if (nums.length == 0) {
            return false;
        }

        int n = nums.length;
        totalSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            totalSum[i] = totalSum[i - 1] + nums[i - 1];
        }

        // O(n^3) time TLE
        // for (int i = 1; i < n; i++) {
        //     for (int j = i + 2; j < n; j++) {
        //         for (int k = j + 2; k < n; k++) {
        //             int sum1 = sumRange(0, i);
        //             int sum2 = sumRange(i + 1, j);
        //             int sum3 = sumRange(j + 1, k);
        //             int sum4 = sumRange(k + 1, n);
        //
        //             if (sum1 == sum2 && sum2 == sum3 && sum3 == sum4) {
        //                 return true;
        //             }
        //         }
        //     }
        // }

        // use hashset + cumulative sum
        // O(n^2)
        for (int j = 3; j < n; j++) {
            HashSet set = new HashSet();
            for (int i = 1; i < j - 1; i++) {

                int sum1 = sumRange(0, i);
                int sum2 = sumRange(i + 1, j);
                if (sum1 == sum2) {
                    set.add(sum1);
                }
            }

            for (int k = j + 2; k < n - 1; k++) {
                int sum3 = sumRange(j + 1, k);
                int sum4 = sumRange(k + 1, n);

                if (sum3 == sum4 && set.contains(sum3)) {
                    return true;
                }
            }

        }
        return false;
    }

    private int[] totalSum;

    public int sumRange(int i, int j) {
        return totalSum[j] - totalSum[i];
    }
}
