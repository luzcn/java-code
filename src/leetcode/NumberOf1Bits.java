package leetcode;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also
 * known as the Hamming weight).
 *
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 */
public class NumberOf1Bits {

  // assume n is unsigned
  public int hammingWeight(int n) {
    int count = 0;

    // we cannot use while(n>0) as loop here
    // because n is unsigned int, 2147483648 (10000000000000000000000000000000) is actually Integer.MIN_VALUE
    // so we need to manually make it loop 32 times

    for (int i = 0; i < 32; i++) {
      if (((n >> i) & 1) == 1) {
        count++;
      }
    }

    return count;
  }
}
