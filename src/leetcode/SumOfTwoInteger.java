package leetcode;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 *
 * Example:
 * Given a = 1 and b = 2, return 3.
 *
 * Thought:
 * bit manipulation
 *
 * a^b is the sum without considering the carry
 *(a&b) << 1 is the carry, but not added yet
 * recursively call the sum function.
 */
public class SumOfTwoInteger {
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }

        int sum = a^b;  // sum without carry
        int carry = (a&b) << 1; // the carry, not added up yet

        return getSum(sum, carry);
    }
}
