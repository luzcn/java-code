package leetcode;

// Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
//
// Return the quotient after dividing dividend by divisor.
//
// The integer division should truncate toward zero.
//
// Example 1:
//
// Input: dividend = 10, divisor = 3
// Output: 3
// Example 2:
//
// Input: dividend = 7, divisor = -3
// Output: -2
public class DivideTwoIntegers {

  public int divide(int dividend, int divisor) {

    if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
      return Integer.MAX_VALUE;
    }

    long m = Math.abs(dividend);
    long n = Math.abs(divisor);

    boolean isNegative = false;
    if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
      isNegative = true;
    }

    long d = n;
    long c = 1;

    while (m > d) {
      d <<= 1;
      c <<= 1;
    }

    int result = 0;

    while (m >= n) {
      while (m >= d) {
        m -= d;
        result += c;
      }

      d >>= 1;
      c >>= 1;
    }

    return isNegative ? 0 - result : result;
  }
}
