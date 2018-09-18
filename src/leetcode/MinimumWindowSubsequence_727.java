package leetcode;

import java.util.*;

// Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
//
// If there is no such window in S that covers all characters in T, return the empty string "".
// If there are multiple such minimum-length windows, return the one with the left-most starting index.
//
// Example 1:
//
// Input:
// S = "abcdebdde", T = "bde"
// Output: "bcde"
// Explanation:
// "bcde" is the answer because it occurs before "bdde" which has the same length.
// "deb" is not a smaller window because the elements of T in the window must occur in order.
public class MinimumWindowSubsequence_727 {
    public String minWindow(String s, String t) {


        HashMap<Character, Integer> map = new HashMap<>();

        // for (char c : t.toCharArray()) {
        //     map.put(c, map.getOrDefault(c, 0) + 1);
        // }


        int begin = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;
        int index = 0;
        int count = 0;

        int pos = 0;

        while (end < s.length()) {
            char c = s.charAt(end++);

            if (c == t.charAt(pos)) {
                pos++;
                count++;
            }

            while (count == t.length()) {
                if (end - begin < minLength) {
                    minLength = end - begin;
                    index = begin;
                }

                char first = s.charAt(begin++);
                if (first == t.charAt(0)) {
                    count--;
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(index, index + minLength);
    }
}
