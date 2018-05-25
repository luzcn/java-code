package leetcode;

import java.util.*;

// Given a non-empty array of integers, every element appears twice except for one. Find that single one.
//
// Note:
//
// Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
//
// Example 1:
//
// Input: [2,2,1]
// Output: 1
// Example 2:
//
// Input: [4,1,2,1,2]
// Output: 4
public class SingleNumber {

    // use bit operator xor can find the single number
    // 1. any number xor 0 is the number itself
    // 2. any number xor itself is 0
    // so, we start from a number = 0, and xor all the number
    public int singleNumber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = 0;

        for (int n : nums) {
            res = (res ^ n);
        }

        return res;
    }


    // 137. Single Number II
    // Given a non-empty array of integers,
    // every element appears three times except for one, which appears exactly once.
    // Find that single one.
    public int singleNumber2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] bit = new int[32];

        // count the bit of ones for each bit position
        // if the total number can mod 3, then put 0, otherwise put 1
        for (int i = 0; i < 32; i++) {
            for (int n : nums) {
                bit[i] += ((n >> i) & 1);
            }

            if (bit[i] % 3 == 0) {
                // 3x numbers have 1 at this bit position, so need to put 0
                bit[i] = 0;
            } else {
                bit[i] = 1;
            }
        }

        // rebuild the number
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (bit[i] == 1) {
                res += (1 << i);
            }
        }

        return res;
    }

    // 260. Single Number III
    // Given an array of numbers nums, in which exactly two elements appear only once
    // and all the other elements appear exactly twice. Find the two elements that appear only once.
    //
    // Example:
    //
    // Input:  [1,2,1,3,2,5]
    // Output: [3,5]

    // In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find.
    // Note that since the two numbers are distinct,
    // so there must be a set bit (that is, the bit with value '1') in the XOR result.
    //
    // Find out an arbitrary set bit (for example, the rightmost set bit).
    //
    // In the second pass, we divide all numbers into two groups, one with the aforementioned bit set,
    // another with the aforementinoed bit unset.
    //
    // Two different numbers we need to find must fall into thte two distrinct groups.
    // XOR numbers in each group, we can find a number in either group.
    public int[] singleNumber3(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            } else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }

}
