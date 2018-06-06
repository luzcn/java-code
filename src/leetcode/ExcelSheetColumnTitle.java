package leetcode;

import java.util.*;

// Given a positive integer, return its corresponding column title as appear in an Excel sheet.
//
// For example:
//
//     1 -> A
//     2 -> B
//     3 -> C
//     ...
//     26 -> Z
//     27 -> AA
//     28 -> AB
//     ...
// Example 1:
//
// Input: 1
// Output: "A"
// Example 2:
//
// Input: 28
// Output: "AB"
// Example 3:
//
// Input: 701
// Output: "ZY"
public class ExcelSheetColumnTitle {

    public String convertToTitle(int n) {

        String res = "";

        while (n > 0) {
            n--;

            res = (char) (n % 26 + 'A') + res;

            n /= 26;
        }

        return res;
    }

    // 171. Excel Sheet Column Number
    // Given a column title as appear in an Excel sheet, return its corresponding column number.
    //
    // For example:
    //
    //     A -> 1
    //     B -> 2
    //     C -> 3
    //     ...
    //     Z -> 26
    //     AA -> 27
    //     AB -> 28
    //     ...
    // Example 1:
    //
    // Input: "A"
    // Output: 1
    // Example 2:
    //
    // Input: "AB"
    // Output: 28
    // Example 3:
    //
    // Input: "ZY"
    // Output: 701
    public int titleToNumber(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int res = 0;
        for (char c : s.toCharArray()) {
            res = res * 26 + c - 'A' + 1;
        }

        return res;
    }
}
