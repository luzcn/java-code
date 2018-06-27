package leetcode;

import java.util.*;

// In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL",
// a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR".
//
// Given the starting string start and the ending string end, return True if and only if
// there exists a sequence of moves to transform one string to the other.
//
// Example:
//
// Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
// Output: True
// Explanation:
// We can transform start to end following these steps:
// RXXLRXRXL ->
// XRXLRXRXL ->
// XRLXRXRXL ->
// XRLXXRRXL ->
// XRLXXRRLX
public class SwapAdjacentInLRString_777 {

    public boolean canTransform(String start, String end) {

        //  greedy
        if (!start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }

        int j = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'L') {
                while (end.charAt(j) != 'L') {
                    j++;
                }

                if (i < j) {
                    // L can only move left
                    return false;
                }
                // move to next char from end string
                j++;
            }
        }

        j = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'R') {
                while (end.charAt(j) != 'R') {
                    j++;
                }

                if (i > j) {
                    // L can only move left
                    return false;
                }

                // move to next char in end
                j++;
            }
        }

        return true;
    }
}
