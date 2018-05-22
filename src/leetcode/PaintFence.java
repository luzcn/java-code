package leetcode;

import java.util.*;

// There is a fence with n posts, each post can be painted with one of the k colors.
//
// You have to paint all the posts such that no more than two adjacent fence posts have the same color.
//
// Return the total number of ways you can paint the fence.
//
// Note:
// n and k are non-negative integers.
//
// Example:
//
// Input: n = 3, k = 2
// Output: 6
// Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
//
//             post1  post2  post3
//  -----      -----  -----  -----
//    1         c1     c1     c2
//    2         c1     c2     c1
//    3         c1     c2     c2
//    4         c2     c1     c1
//    5         c2     c1     c2
//    6         c2     c2     c1
public class PaintFence {
    // DP solution
    // for each fence, there are two sub problems: use the same color of previous one or different color
    // - use same[i] to indicate the painting ways of ith fence, if we use the same color with i-1 fence
    // - use diff[i] to indicate the painting ways of ith fence, if we use the difference color with i-1 fence
    // so, we can have
    // - same[i] = diff[i-1], we cannot use same[i-1] here, because no more than two adjacent fence posts have the same color.
    // - diff[i] = (same[i-1] + diff[i-1]) * (k-1), at ith fence, we have k-1 choices and there are (same[i-1] + diff[i-1])  ways to paint previous fences

    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }

        if (n == 1) {
            return k;
        }

        // if we have 2 fences,
        // there are k ways to paint same colors
        // k*(k-1) ways to paint different colors
        int same = k;
        int diff = k * (k - 1);

        for (int i = 2; i < n; i++) {
            int temp = diff;

            diff = (same + diff) * (k - 1);
            same = temp;
        }

        return same + diff;
    }
}
