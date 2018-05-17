package leetcode;

import java.util.*;

// Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
//
// Note:
// n is positive and will fit within the range of a 32-bit signed integer (n < 231).
//
// Example 1:
// Input:
// 3
// Output:
// 3
//
// Example 2:
// Input:
// 11
// Output:
// 0
// Explanation:
// The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
public class NthDigit {

    // number 1...9 has 1 digit
    // [10...99] has 2 digits
    // [100 ....999] 3 digits
    // so we use a variable "count" to indicate currently in which range, for each iteration, count*10
    // use variable "length" to record the numbers in current range
    // and variable "start"
    public int findNthDigit(int n) {

        // find the length of the number where the nth digit is from
        // find the actual number where the nth digit is from
        // find the nth digit and return
        long count = 9;
        int length = 1;
        int start = 1;

        while (n > length * count) {
            n -= length * count;

            length++;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / length;
        String number = Integer.toString(start);

        return Character.getNumericValue(number.charAt((n - 1) % length));
    }
}
