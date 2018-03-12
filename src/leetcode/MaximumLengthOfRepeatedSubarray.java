package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * a.k.a the longest common subarray, similar to longest common substring, or longest common subsequence
 * Example 1: Input: A: [1,2,3,2,1] B: [3,2,1,4,7] Output: 3 Explanation: The repeated subarray with maximum length is
 * [3, 2, 1].
 */
public class MaximumLengthOfRepeatedSubarray {


    // DP solution, similar to LongestCommonSubString problem
    public int findLength(int[] A, int[] B) {

        int m = A.length;
        int n = B.length;
        int maxLength = 0;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // for (int[] s : dp) {
        //     System.out.println(String.join(",", Arrays.stream(s).
        //             mapToObj(a -> Integer.toString(a)).collect(Collectors.toList())));
        // }


        return maxLength;

    }

    // brute force solution,
    // since most of the A[i]!=B[j],
    // we can use a hashmap to save the start index of A[i] in B array
    private int find(int[] A, int B[]) {
        int ans = 0;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int j = 0; j < B.length; j++) {
            map.computeIfAbsent(B[j], (x) -> new ArrayList<>()).add(j);
        }

        for (int i = 0; i < A.length; i++) {
            for (int j : map.getOrDefault(A[i], new ArrayList<>())) {
                int k = 0;
                while (i + k < A.length && j + k < B.length && A[i + k] == B[j + k]) {
                    k++;
                    ans = Math.max(ans, k);
                }
            }
        }

        return ans;
    }
}
