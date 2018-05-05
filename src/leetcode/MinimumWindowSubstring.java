package leetcode;

import java.util.HashMap;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * - If there is no such window in S that covers all characters in T, return the empty string "".
 * - If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

    // the magic two-pointer + hash map pattern


    public String minWindow(String s, String t) {
        if (t == null || t.isEmpty()) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<>();

        // count each character frequency and put in hash map
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int count = t.length();
        int begin = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;
        int index = 0;

        while (end < s.length()) {
            char c = s.charAt(end++);

            if (map.containsKey(c) && map.get(c) > 0) {
                // if the character c is from string t and still available to use
                // reduce the count
                count--;
            }

            // decrease the frequency of c
            // no matter it's from string t or not
            // if it's from t, the value could be >= 0
            // others will be negative
            map.put(c, map.getOrDefault(c, 0) - 1);


            while(count == 0) {
                if (end - begin < minLength) {
                    minLength = end - begin;
                    index = begin;
                }

                // update the map, try to move "begin" as far as possible
                char first = s.charAt(begin++);

                if (map.get(first) == 0) {
                    count++;
                }

                map.put(first, map.get(first) + 1);
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(index, index + minLength);
    }

}
