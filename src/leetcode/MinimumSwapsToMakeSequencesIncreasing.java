package leetcode;

/**
 * We have two integer sequences A and B of the same non-zero length.
 *
 * We are allowed to swap elements A[i] and B[i].
 * Note that both elements are in the same index position in their respective sequences.
 *
 * At the end of some number of swaps, A and B are both strictly increasing.
 * (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 *
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.
 *
 * It is guaranteed that the given input always makes it possible.
 *
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 * Note:
 *
 * A, B are arrays with the same length, and that length will be in the range [1, 1000].
 * A[i], B[i] are integer values in the range [0, 2000].
 */
public class MinimumSwapsToMakeSequencesIncreasing {


    public int minSwap(int[] A, int[] B) {
        // DP

        // n1 indicate the cost of making [0...i-1] strictly increasing if do not swap at i-1
        // s1 indicate the cost of making [0...i-1] strictly increasing if swap at i-1
        int n1 = 0;
        int s1 = 1;

        for (int i = 1; i < A.length; i++) {
            int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;


            // if we have A[i - 1] < A[i] && B[i - 1] < B[i],
            // we can keep both i-1 and i unswap, so n2 = n1;
            // or we can swap both i-1 and i, so s2 = s1 + 1;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }


            // if we have A[i - 1] < B[i] && B[i - 1] < A[i],
            // then, we can swap i-1, and not swap i, so n2 = min(n2, s1);
            // or, keep i-1 but swap i, so s2 = min(s2, n1 + 1);
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                n2 = Math.min(n2, s1);
                s2 = Math.min(s2, n1 + 1);
            }

            n1 = n2;
            s1 = s2;
        }

        return Math.min(n1, s1);
    }

    private int dfs(int[] A, int[] B, int i, int prevA, int prevB) {
        if (i >= A.length) {
            return 0;
        }

        if (prevA < A[i] && prevB < B[i]) {
            return Math.min(dfs(A, B, i + 1, A[i], B[i]), dfs(A, B, i + 1, B[i], A[i]) + 1);
        }

        // prevA >= A[i] or prevB >= B[i], you have to swap
        if (prevA < B[i] && prevB < A[i]) {
            return dfs(A, B, i + 1, B[i], A[i]) + 1;
        }

        return Integer.MAX_VALUE - 1;
    }
}
