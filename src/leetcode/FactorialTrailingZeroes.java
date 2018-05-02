package leetcode;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Example 1:
 *
 * Input: 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 *
 * Input: 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 */
public class FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        // only 2 *5 can generate 0
        // and if we find 5 in the production sequence, we can always find 2
        // and 10= 2*5, 20 = 2*2*5
        int res = 0;
        while (n >= 5) {
            res += n / 5;
            n /= 5;
        }

        return res;
    }
}
