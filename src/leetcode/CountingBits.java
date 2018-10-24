package leetcode;

// Given a non negative integer number num.
// For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation
// and return them as an array.
//
// Example:
// For num = 5 you should return [0,1,1,2,1,2].
//
// Follow up:
// - It is very easy to come up with a solution with run time O(n*sizeof(integer)).
// But can you do it in linear time O(n) /possibly in a single pass?
// Space complexity should be O(n).
public class CountingBits {

  // brute force solution,counting 32-bit position for each number, totally takes O(n*32)
  // we can use the dp idea, for each number n, we count the number "x" of last contiguous bit "1"
  // now the new number of 1's is the dp[n-1] - x + 1;
  public int[] countBits(int num) {

    int[] res = new int[num];
    res[0] = 0;

    for (int i = 1; i < num; i++) {
      int n = i - 1;
      int x = 0;

      while (n > 0 && ((n & 1) == 1)) {
        x++;
        n >>= 1;
      }

      res[i] = res[i - 1] - x + 1;
    }

    return res;
  }
}
