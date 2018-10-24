package leetcode;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1: Input: 123 Output:  321
 *
 * Example 2: Input: -123 Output: -321
 *
 * Example 3: Input: 120 Output: 21
 */
public class ReverseInteger {

  public int reverse(int x) {
    int res = 0;
    while (x != 0) {
      // the INT_MAX is 2147483647, if abs(ret) > 214748364, ret*10 will be at least 2147483650 overflow.
      // we do not need to compare == 214748364, because number between 2147483641...2147483647 is still valid.
      if (Math.abs(res) > Integer.MAX_VALUE / 10) {
        return 0;
      }

      res = res * 10 + x % 10;
      x /= 10;
    }

    return res;
  }
}
