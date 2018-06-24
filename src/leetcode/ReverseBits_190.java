package leetcode;

import java.util.*;

// Reverse bits of a given 32 bits unsigned integer.
//
// Example:
//
// Input: 43261596
// Output: 964176192
// Explanation: 43261596 represented in binary as 00000010100101000001111010011100,
//              return 964176192 represented in binary as 00111001011110000010100101000000.
// Follow up:
// If this function is called many times, how would you optimize it?
public class ReverseBits_190 {
    public int reverseBits(int n) {

        int res = 0;

        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                res += (1 << (31 - i));
            }
        }

        return res;
    }
}
