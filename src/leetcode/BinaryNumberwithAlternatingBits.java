package leetcode;

/**
 * Given a positive integer, check whether it has alternating bits: namely,
 * if two adjacent bits will always have different values.
 *
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation:
 * The binary representation of 5 is: 101
 */
public class BinaryNumberwithAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        if (n == 0)
            return true;

        int c1 = (n & 1);
        int c2 = ((n >> 1) & 1);

        return (c1 != c2 && hasAlternatingBits(n >> 1));
    }
}
