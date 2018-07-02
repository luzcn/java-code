package leetcode;

import java.util.*;

// X is a good number if after rotating each digit individually by 180 degrees,
// we get a valid number that is different from X.
//
// Each digit must be rotated - we cannot choose to leave it alone.
//
// A number is valid if each digit remains a digit after rotation.
// 0, 1, and 8 rotate to themselves;
// 2 and 5 rotate to each other;
// 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
//
// Now given a positive number N, how many numbers X from 1 to N are good?
//
// Example:
// Input: 10
// Output: 4
// Explanation:
// There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
// Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
public class RotatedDigits_788 {

    public int rotatedDigits(int N) {
        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (good(i)) {
                res++;
            }
        }

        return res;
    }

    // brute force
    // if the number contains 3, 4, 7, then not valid
    // if the last digit is 0, 1, 8, then keep searching
    // if the last digit is 2,5,6,9, keep searching with flag as true
    //
    // All we need is to check if the number has at least one [2,5,6,9] and has no 3,4,7
    private boolean good(int n) {
        String s = String.valueOf(n);
        if (s.contains("3") || s.contains("4") || s.contains("7")) {
            return false;
        }

        return (s.contains("2") || s.contains("5") || s.contains("6") || s.contains("9"));
    }

    // private boolean good(int n, boolean flag) {
    //     if (n == 0) {
    //         return flag;
    //     }
    //
    //     int d = n % 10;
    //     if (d == 3 || d == 4 || d == 7) {
    //         return false;
    //     }
    //
    //     if (d == 0 || d == 1 || d == 8) {
    //         return good(n / 10, flag);
    //     }
    //
    //     return good(n / 10, true);
    // }
}